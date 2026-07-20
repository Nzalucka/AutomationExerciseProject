package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static Object[][] readSearchTerms() throws Exception {
        List<Object[]> data = new ArrayList<>();
        Reader reader = new FileReader("src/test/resources/search-terms.csv");
        CSVParser csv =
                CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build().parse(reader);
        for (CSVRecord record : csv) {
            data.add(new Object[]{
                    record.get("search_term"),
                    record.get("expected_first_product")
            });
        }
        return data.toArray(new Object[0][]);
    }}
