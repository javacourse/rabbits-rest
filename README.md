# rabbits-rest
RESTful API для прототипа модели потребления энергии кроликами великанами

Запуск:
mvn spring-boot:run

Документация REST ендпоинтов через swagger, запустить приложение, открыть локальный файл:
\rabbits-rest\src\main\resources\static\swagger-ui\index.html

Для swagger локально возможны проблемы с CORS запросами, можно потестить через Postman (плагин для Chrome), коллекция в архиве Rabbits.json.postman_collection

Формат данных указывается в заголовках запросов:
"Accept: application/json" – для JSON
"Accept: application/yaml" – для YAML
