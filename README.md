# distance-calculator
Сервис запускается на 8888 порту, при запуске к базе данных 'distance-calculator' автоматически применяются миграции Liquibase.
В ходе разработки, для упрощения логики, сделано допущение, что не существует городов с одинаковыми названиями.

Эндпоинты:
  1) /cities - список всех городов в базе (GET-запрос без параметров)
  2) /upload - загрузка данных из XML-файла в базу данных (POST-запрос в теле которого multipart/form-data файл со структурой, как в 'testFile.xml')
  3) /calculate-distance - расчет расстояния (POST-запрос в теле которого JSON со структурой по указанному ниже образцу)
  

Структура JSON:  
  {
   "calculationType": "ALL",
   "fromCity": ["Самара", "Тамбов"],
   "toCity": ["Москва", "Тверь"]
  }

Параметр 'calculationType' может принимать значения CROWFLIGHT, DISTANCE_MATRIX, ALL.
