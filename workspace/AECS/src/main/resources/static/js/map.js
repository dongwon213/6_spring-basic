window.onload = function() {
    console.log("window.onload event triggered");

    var map, startLatLng, chargingLatLng, endLatLng;

    function initKakaoMap() {
        console.log("Initializing Kakao Map");
        var container = document.getElementById('map');
        var options = {
            center: new kakao.maps.LatLng(37.5665, 126.9780), // 서울 중심 좌표
            level: 3
        };
        map = new kakao.maps.Map(container, options);

        // AJAX 요청으로 충전소 데이터를 가져옴
        fetch('/home/stations')
            .then(response => response.json())
            .then(chargingStations => {
                console.log('chargingStations:', chargingStations); // 데이터가 제대로 불러오는지 확인
                var activeInfoWindow = null; // 현재 열려있는 정보 창을 저장할 변수
                var zIndexCounter = 1; // z-index를 위한 카운터

                chargingStations.forEach(function(station) {
                    if (!station.latitude || !station.longitude) {
                        console.warn('Invalid station data:', station);
                        return; // 위도 경도 정보가 없으면 무시
                    }

                    var markerPosition = new kakao.maps.LatLng(station.latitude, station.longitude);

                    var markerImage = new kakao.maps.MarkerImage(
                        'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png',
                        new kakao.maps.Size(24, 35),
                        { offset: new kakao.maps.Point(12, 35) }
                    );

                    var marker = new kakao.maps.Marker({
                        position: markerPosition,
                        image: markerImage // 마커 이미지 설정
                    });

                    marker.setMap(map);

                    var chargerInfoMap = new Map();
                    station.chargers.forEach(charger => {
                        if (!chargerInfoMap.has(charger.chargerType)) {
                            chargerInfoMap.set(charger.chargerType, { total: 0, available: 0, type: charger.carChargingType });
                        }
                        chargerInfoMap.get(charger.chargerType).total++;
                        if (charger.chargerStatus === '사용 가능') {
                            chargerInfoMap.get(charger.chargerType).available++;
                        }
                    });

                    var chargersInfo = '';
                    chargerInfoMap.forEach((info, type) => {
                        var chargingType = info.type === '급속' ? '급속' : '완속';
                        chargersInfo += `<p>(${chargingType}) ${type} : ${info.available} / ${info.total}</p>`;
                    });

                    var infoContent = `<div class="info-container" style="z-index:${zIndexCounter}">
                        <div class="info-window">
                            <p><strong>${station.stationName}</strong></p>
                            <p>급속: ${station.fastChargerPrice || 'undefined'}원 / 완속: ${station.slowChargerPrice || 'undefined'}원</p>
                            ${chargersInfo}
                            <a href="#" class="charge-button">충전소 선택</a>
                        </div>
                    </div>`;

                    var infowindow = new kakao.maps.InfoWindow({
                        content: infoContent,
                        position: markerPosition,
                        yAnchor: 1 // 인포윈도우의 위치를 위로 올림
                    });

                    var isOpen = false;

                    kakao.maps.event.addListener(marker, 'click', function() {
                        if (isOpen) {
                            infowindow.close();
                            isOpen = false;
                        } else {
                            if (activeInfoWindow) {
                                activeInfoWindow.close();
                            }

                            var infoWindowContent = infowindow.getContent();
                            var parser = new DOMParser();
                            var infoWindowDiv = parser.parseFromString(infoWindowContent, 'text/html').body.firstChild;

                            if (infoWindowDiv) {
                                var infoWindow = infoWindowDiv.querySelector('.info-window');
                                var infoContainer = infoWindowDiv.querySelector('.info-container');

                                if (infoWindow) {
                                    infoWindow.style.display = 'block';
                                    if (infoContainer) {
                                        infoContainer.style.zIndex = zIndexCounter++;
                                    }

                                    infowindow.setContent(infoWindowDiv.outerHTML);
                                }
                            }

                            infowindow.open(map, marker);
                            activeInfoWindow = infowindow;
                            isOpen = true;
                        }
                    });
                });

                // 차량 위치 정보 가져오고 경로 찾기 로직 초기화
                getCarLocationAndUpdateRoute();
            })
            .catch(error => console.error('Error:', error));
    }

    // Kakao Maps API를 비동기로 로드
    function loadKakaoMapScript() {
        var script = document.createElement('script');
        script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=19d09a30c5edc8b9c363a4d06eb596e6&autoload=false&libraries=services";
        script.onload = function() {
            console.log("Kakao Map script loaded");
            kakao.maps.load(initKakaoMap);
        };
        document.head.appendChild(script);
    }

    console.log("Loading Kakao Map script");
    loadKakaoMapScript();

    function getCarLocationAndUpdateRoute() {
        // 차량의 현재 위치 가져오기
        fetch('/map/car/location?carId=17')  // carId를 실제 차량 ID로 변경
            .then(response => response.json())
            .then(data => {
                console.log('Car location data:', data);
                startLatLng = new kakao.maps.LatLng(data.latitude, data.longitude);

                document.getElementById('stationForm').addEventListener('submit', function(event) {
                    event.preventDefault();
                    var stationName = document.getElementById('station').value;
                    // 충전소 좌표를 구하는 로직을 여기에 추가
                    searchLocation(stationName, function(result) {
                        chargingLatLng = new kakao.maps.LatLng(result[0].y, result[0].x);
                    });
                });

                document.getElementById('arrivalForm').addEventListener('submit', function(event) {
                    event.preventDefault();
                    var arrivalName = document.getElementById('arrival').value;
                    // 도착지 좌표를 구하는 로직을 여기에 추가
                    searchLocation(arrivalName, function(result) {
                        endLatLng = new kakao.maps.LatLng(result[0].y, result[0].x);
                    });
                });

                document.getElementById('findRoute').addEventListener('click', function() {
                    displayRoute();
                });
            })
            .catch(error => {
                console.error('Error fetching car location:', error);
            });
    }

    function searchLocation(query, callback) {
        var places = new kakao.maps.services.Places();
        places.keywordSearch(query, function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                callback(result);
            } else {
                alert('해당 장소를 찾을 수 없습니다.');
            }
        });
    }

    function displayRoute() {
        if (!startLatLng || !chargingLatLng || !endLatLng) {
            alert('모든 좌표를 입력해주세요.');
            return;
        }

        var directionsService = new kakao.maps.services.Directions();

        var start = {
            x: startLatLng.getLng(),
            y: startLatLng.getLat()
        };

        var goal = {
            x: endLatLng.getLng(),
            y: endLatLng.getLat()
        };

        var waypoints = [
            {
                x: chargingLatLng.getLng(),
                y: chargingLatLng.getLat()
            }
        ];

        directionsService.route({
            origin: start,
            destination: goal,
            waypoints: waypoints,
            priority: "RECOMMEND",
            apiKey: "3e8fd2c0758ff43df831c9bee8128741"
        }, function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                var routePath = result.route[0].sections[0].roads[0].vertexes;
                var path = [];
                for (var i = 0; i < routePath.length; i += 2) {
                    path.push(new kakao.maps.LatLng(routePath[i + 1], routePath[i]));
                }
                var polyline = new kakao.maps.Polyline({
                    path: path,
                    strokeWeight: 5,
                    strokeColor: '#FF0000',
                    strokeOpacity: 0.8,
                    strokeStyle: 'solid'
                });
                polyline.setMap(map);
            } else {
                alert('경로를 찾을 수 없습니다.');
            }
        });
    }
};
