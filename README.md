<a href="https://github.com/5h15h4k1n9/computer-shop/actions"><img alt="Build Status" src="https://github.com/5h15h4k1n9/computer-shop/actions/workflows/build.yml/badge.svg"></a>
<a href="https://github.com/5h15h4k1n9/computer-shop/actions"><img alt="Test Status" src="https://github.com/5h15h4k1n9/computer-shop/actions/workflows/test.yml/badge.svg"></a>
![GitHub](https://img.shields.io/github/license/5h15h4k1n9/computer-shop?color=blue&logo=apache)
[![CodeFactor](https://www.codefactor.io/repository/github/5h15h4k1n9/computer-shop/badge)](https://www.codefactor.io/repository/github/5h15h4k1n9/computer-shop)
![GitHub repo size](https://img.shields.io/github/repo-size/5h15h4k1n9/computer-shop?logo=github&color=green)
![GitHub top language](https://img.shields.io/github/languages/top/5h15h4k1n9/computer-shop?logo=github&color=orange)

# Computer shop

This is a simple computer shop application for EasyBot task.

## How to run

This application is written on Java 17 using SpringBoot framework and uses Maven as a build tool.

### Prerequisites
- JDK 17 or higher
- Maven 3.9.2 or higher

### Running on Linux/MacOS

1. Clone this repository using the following command:
```shell
git clone https://github.com/5h15h4k1n9/computer-shop.git
```
2. Go to the project directory:
```shell
cd computer-shop
```
3. Build the package:
```shell
./mvnw clean package
```
4. Run the application:
```shell
java -jar target/computer-shop-<version>.jar
```