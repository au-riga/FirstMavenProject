# FirstMavenProject
Это библиотека. В ней есть функция getRandomString() (класс
org.example.RandomStringGenerator), которая возвращает случайную строку
из 10 символов. Саму библиотеку запускать не нужно, её используют
другие мои проекты: ConsoleApp и JavaFXApp.

## Установка окружения (WSL2 + Ubuntu)

В PowerShell от имени администратора:
wsl --install -d Ubuntu

Внутри Ubuntu:

sudo apt update && sudo apt upgrade -y  обновляет список доступных программ и их версии
sudo apt install -y openjdk-25-jdk maven git - устанавливает java 25 JDK, maven и git

echo 'export JAVA_HOME=/usr/lib/jvm/java-25-openjdk-amd64' >> ~/.bashrc  создает переменную JAVAHOMME записку с путем java. По ней maven и др. программы находят java 
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bashrc  это список папок,где терминал ищет программы, когда пишешь java или javac
source ~/.bashrc 

java -version 
mvn -v

## Сборка

./build.sh

Скрипт запускает Maven с профилем local-deploy. Maven компилирует код,
прогоняет тесты, собирает два jar (сама библиотека и jar с исходниками)
и деплоит их в папку ~/maven-repo. Если тесты упадут, скрипт
остановится и ничего не задеплоит

Сборка без скрипта:
mvn clean deploy -P local-deploy

Cобрать и прогнать тесты:
mvn clean package 

## Пути

Рабочая копия проекта ~/projects/FirstMavenProject
Bare-репозиторий      ~/git-repos/FirstMavenProject.git
Репозиторий с библиотекой(сюда она загружается)  ~/maven-repo 

Скачать проект из bare-репозитория:

git clone ~/git-repos/FisrMavenProject.git

## Как подключить библиотеку в другом проекте

В pom.xml другого проекта нужно указать, где искать библиотеку,
и добавить зависимость:

    <repositories>
        <repository>
            <id>local-repo</id>
            <url>file://${user.home}/maven-repo</url>
        </repository>
    </repositories>

    <dependency>
        <groupId>org.example</groupId>
        <artifactId>FirstMavenProject</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>


## Что исключено в .gitignore и почему

В Git я храню только то, что пишется руками: код, pom.xml, скрипт.
Всё, что появляется при сборке или зависит от моего компьютера, я исключил:

- target/ - сюда Maven складывает результат сборки. Его не нужно хранить,
  потому что он заново появляется после mvn package.
- dependency-reduced-pom.xml - этот файл сам создаёт плагин shade при сборке.
  Я заметил его в git status и добавил в исключения.
- .idea/, *.iml, .vscode/, .settings/, .classpath, .project - настройки IDE.
  У каждого разработчика они свои, в общем репозитории им не место.
- Thumbs.db, Desktop.ini, .DS_Store - служебный мусор Windows и macOS.
- *.log, *.tmp, *.bak, *~ - логи и временные файлы.
- dist/ - на случай, если готовые файлы будут копироваться внутрь проекта.

Папка деплоя ~/maven-repo лежит вне проекта, поэтому в Git она и так не попадает

Скрипт build.sh и файл .gitattributes я НЕ исключал, потому что это часть проекта.
В .gitattributes написано правило *.sh text eol=lf. Оно нужно, чтобы скрипт
всегда хранился с линуксовыми переводами строк. Если файл попадёт с
виндовыми переводами строк (CRLF), скрипт не запустится.
