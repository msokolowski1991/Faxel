# Faxel
Faxel is an excel to java object mapper library.
By annotating your java classes, You can define how an excel should be represented in Java world. Thank's to faxel you no longer need to manually parse excel to Java!
## Example usage
### Java class model
First of all, we need our java classes. Imagine a simple excel containing people. We can annotate it like this:
```
class PersonDataExcel {
    @ExcelSheet(sheetName = "Person", start = 1, end = Integer.MAX_VALUE, arrangement = DataArrangementType.ROW)
    private Collection<Person> people;
}

public class Person {
    @Cell(index = 0)
    private String firstName;

    @Cell(index = 1)
    private String lastName;
}
```
As you can see, first we've annotated a collection of Person as @ExcelSheet. This means that sheet named "Person", starting from position 1 to Integer.MAX_VALUE, will be parsed row by row. The startIndex should be less than end. The arrangement could be one of ROW or COLUMN.
### Parsing excel
Next step is to actually parse our excel to previously defined Java model. First we need to create ModelDefinition from our class.
```
    ModelDefinition model = ModelDefinitionFactory.get().create(PersonDataExcel.class);
```
This model can be reused as many times as you need. Next step is to actually fill instance of our model using an excel source.
```
    InputStream excelStream = getClass().getResourceAsStream("/person-data.xlsx");
    SourceExcel source = SourceFactory.get().create(excelStream);
    model.fill(source, new PersonDataExcel());
```
A SourceExcel class is abstraction over actual parsing library implementation.
You can obtain it using SourceFactory which will determine your runtime parsing library or create directly.
For now faxel.poi.ApachePoiSource is the only one implementation but more will come soon in next releases.

That's it! We successfully parsed an entire excel!
## Types
### Built in types support
By default Faxel support following types:
- String
- Integer/int
- Long/long
- Short/short
- Float/float
- Double/double
- Boolean/boolean
- Date
- LocalDate
- LocalDateTime
- LocalTime
### Custom converter
If you like to parse cell to type Faxel does not support out of the box, you may create your own converter by implementing Converter interface:
```
    faxel.converter.ColumnConverter
```
Then use it in @Cell annotation
```
    @Cell(index = 5, converter = CustomBigDecimalConverter.class)
```