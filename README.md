# Faxel
Faxel is an excel to java object mapper library.
By annotating your java classes, You can define how an excel should be represented in Java world. Thank's to faxel you no longer need to manually parse excel to Java!
## Maven Dependency
```
<dependency>
  <groupId>pl.net.falcon</groupId>
  <artifactId>faxel</artifactId>
  <version>1.1.1</version>
</dependency>
```
## Example usage
This section will guide through simple example of how to use Faxel.

#### Java class model
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

#### Model Definition
Next step is to actually parse our excel to previously defined Java model. First we need to create ModelDefinition from our class.
```
    ModelDefinition model = ModelDefinitionFactory.get().create(PersonDataExcel.class);
```
This model can be reused as many times as you need. Next step is to get a SourceExcel.

#### SourceExcel
A SourceExcel class is abstraction over actual parsing library implementation.
You can obtain it using SourceFactory which will determine your runtime parsing library:
```
    InputStream excelStream = getClass().getResourceAsStream("/person-data.xlsx");
    SourceExcel source = SourceFactory.get().create(excelStream);
```
or create it directly:
```
    InputStream excelStream = getClass().getResourceAsStream("/person-data.xlsx");
    Workbook workbook = WorkbookFactory.create(excelStream)
    SourceExcel source = new ApachePoi3Source(workbook);
```
For now faxel.apache.poi.ApachePoi3Source which supports Apache POI 3.(6+) is the only one implementation but more will come soon in next releases.

#### Parsing excel
Then finally we can fill our model using SourceExcel. The source is used by previously created ModelDefinition to actually parse an excel:
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
#### 1.2.0
- Added support of docx4j 6.x.x
- Introduced SourceType enum.
- SourceFactory can be created using SourceType enum
#### 1.1.1
- Fixed issue with column arrangement indexes
- Fixed issue with incorrect data read from cell
#### 1.1.0
- Fixed issue with @ExcelSheet field type. Now any child of java.util.Collection is supported.
- Added few helper methods to *Source classes.
- Internal code refactoring
#### 1.0.0
- First Release version
# Contact
You may contact me at: michal.sokolowski@falcon.net.pl