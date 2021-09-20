# distance-calculator
Сервис запускается на 8888 порту, при запуске к базе данных 'distance-calculator' автоматически применяются миграции Liquibase.

Эндпоинты:
  1) /cities - список всех городов в базе (GET-запрос без параметров)
  2) /upload - загрузка данных из XML-файла в базу данных (POST-запрос в теле которого multipart/form-data файл со структурой, как в 'testFile.xml')
  3) /calculate-distance - расчет расстояния (POST-запрос в теле которого JSON со структурой как указано ниже)
  

Структура JSON:  
  {
   "calculationType": "ALL",
   "fromCity": ["Самара", "Тамбов"],
   "toCity": ["Москва", "Тверь"]
  }
