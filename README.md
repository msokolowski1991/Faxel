# Faxel
Faxel is an excel to java object mapper library.
By annotating your java classes, You can define how an excel should be represented in Java world. Thank's to faxel you no longer need to manually parse excel to Java!
## Example usage
### Java class model
First of all, we need our java classes. Imagine a simple excel containing people. We can annotate it like this:
```
class PersonDataExcel {
    @ExcelSheet(sheetName = "Person", startPosition = 1, maxPosition = Integer.MAX_VALUE, arrangement = DataArrangementType.ROW)
    private Collection<Person> people;
}

public class Person {
    @Cell(index = 0)
    private String firstName;

    @Cell(index = 1)
    private String lastName;
}
```
As you can see, first we've annotated a collection of Person as @ExcelSheet. This means that sheet named "Person", starting from position 1 to Integer.MAX_VALUE, will be parsed row by row. The startPosition should be less than maxPosition. The arrangement could be one of ROW or COLUMN.
### Parsing excel
Next step is to actually parse our excel to previously defined Java model. First we need to create ModelDefinition from our class.
```
    ModelDefinition model = ModelDefinitionFactory.get().create(PersonDataExcel.class);
```
This model can be reused as many times as you need. Next step is to get an excel source
```
    // Using SourceFactory which detects our runtime parsing library
    InputStream excelStream = getClass().getResourceAsStream("/person-data.xlsx");
    SourceExcel source = SourceFactory.get().create(excelStream);
    // OR directly point to ExcelSource implementation
    InputStream excelStream = getClass().getResourceAsStream("/person-data.xlsx");
    Workbook workbook = WorkbookFactory.create(excelStream)
    SourceExcel source = new ApachePoi3Source(workbook);
```
A SourceExcel class is abstraction over actual parsing library implementation.
You can obtain it using SourceFactory which will determine your runtime parsing library or create it directly.
For now faxel.apache.poi.ApachePoi3Source which supports Apache POI 3.(6+) is the only one implementation but more will come soon in next releases.

Then finally we can fill our model using SourceExcel
```
    PersonDataExcel result = model.fill(source, new PersonDataExcel());
```
The result model is actually the same as argument so you can ignore result and use argument reference.

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
## Release info
Release Candidate 3
# Contact
You may contact me at: michal.sokolowski@falcon.net.pl