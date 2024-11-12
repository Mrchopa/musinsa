# MUSINSA coding test

## 프로젝트 개요
MUSINSA Coding Test 프로젝트는 의류 카테고리별 최저 가격 조회 API와 관련 상품 관리를 위한 REST API를 구현한 코딩 테스트 프로젝트입니다.

## 구현 범위
프로젝트는 다음의 주요 기능들을 포함합니다:
1. **카테고리별 최저 가격 상품 조회** - 각 의류 카테고리에서 가장 저렴한 상품 정보를 제공합니다.
2. **단일 브랜드 최저가 상품 조회** - 하나의 브랜드로 전체 코디네이션을 구성할 때 최저가 옵션을 제공합니다.
3. **카테고리별 최저 및 최고 가격 조회** - 특정 카테고리에서의 최저 및 최고가 상품 정보를 조회합니다.
4. **브랜드 및 상품 추가, 수정, 삭제** - 브랜드와 상품 정보를 관리할 수 있습니다.

## 코드 빌드, 테스트, 실행 방법
### 1. 환경 설정
- Java 17 (JAVA 21 권장)
- Gradle
- Docker (Optional)

### 2. 빌드 및 실행
#### artifact build & run
```shell
./gradle build
./gradle bootRun
```
#### container build & run
```shell
docker build -f .
docker start
```
### 3. 테스트  실행
```shell
./gradle test
```

## 추가 정보
- H2 DB를 사용하고, 실행 시 초기 데이터가 로드됩니다. (resources/schema.sql, resources/data.sql)
- API 문서는 OAS 문서로 제공됩니다. (/swagger-ui/index.html)
- Virtual thread가 활성화 되어 있습니다. (JRE 21 환경에서 default)