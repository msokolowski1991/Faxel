# Faxel
Faxel is an excel to java object mapper library.
By annotating your java classes, You can define how an excel should be represented in Java world. Thank's to faxel you no longer need to manually parse excel to Java!
## Example usage
### Java class model
First of all, we need our java classes. Imagine a simple excel containing people. We can annotate it like this:
```
class PersonDataExcel {
    @SheetRows(sheetName = "Person", firstDataRow = 2)
    private Collection<Person> people;
}

public class Person {
    @Column(index = 0)
    private String firstName;

    @Column(index = 1)
    private String lastName;
}
```
### Parsing excel
Next step is to actually parse our excel to previously defined Java model. First we need to create ModelDefinition from our class.
```
    InputStream excelStream = getClass().getResourceAsStream("/person-data.xlsx")
    ModelDefinition model = ModelDefinitionFactory.get().create(PersonDataExcel.class)
```
This model can be reused as many times as you need. Next step is to actually fill instance of our model using an excel source.
```
    model.fill(WorkbookFactory.create(excelStream), new PersonDataExcel())
```
And that's it! We successfully parsed an entire excel!
## Types
### Built in types support
By default Faxel support following types:
1. String
2. Integer/int
3. Long/long
4. Short/short
5. Float/float
6. Double/double
7. Boolean/boolean
8. Date
9. LocalDate
10. LocalDateTime
11. LocalTime
### Custom converter
If you like to parse cell to type Faxel does not support out of the box, you may create your own converter by implementing Converter interface:
```
    faxel.converter.ColumnConverter
```
Then use it in @Column annotation
```
    @Column(index = 5, converter = CustomBigDecimalConverter.class)
```