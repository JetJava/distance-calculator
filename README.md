# distance-calculator
Сервис запускается на 8888 порту, при запуске к базе данных 'distance-calculator' автоматически применяются миграции Liquibase.

Эндпоинты:
  1) /cities - список всех городов в базе (GET-запрос без параметров)
  2) /upload - загрузка данных из XML-файла в базу данных (POST-запрос в теле которого multipart/form-data файл со структурой, как в 'testFile.xml')
  3) /calculate-distance - расчет расстояния (POST-запрос в теле которого JSON со структурой как указано ниже)
  
  
Содержание файла 'testFile.xml':
  "<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
  <UploadData>
      <Cities>
          <City>
              <Name>Самара</Name>
              <Latitude>53.20007</Latitude>
              <Longitude>50.15000</Longitude>
          </City>
          <City>
              <Name>Москва</Name>
              <Latitude>55.75222</Latitude>
              <Longitude>37.61556</Longitude>
          </City>
     </Cities>
      <Distances>
          <Distance>
              <FromCityName>Самара</FromCityName>
              <ToCityName>Москва</ToCityName>
              <Distance>1100.0</Distance>
          </Distance>
      </Distances>
  </UploadData>"
  
Структура JSON:  
  {
   "calculationType": "ALL",
   "fromCity": ["Самара", "Тамбов"],
   "toCity": ["Москва", "Тверь"]
  }
