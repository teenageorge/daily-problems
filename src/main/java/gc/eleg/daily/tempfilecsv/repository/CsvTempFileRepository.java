package gc.eleg.daily.tempfilecsv.repository;

public class CsvTempFileRepository implements FileRepository {

    @Override
    public String getCsvString() {
        return getCsvStringFromJsonFile();
    }

    private String getCsvStringFromJsonFile() {
        return "1 - en,1 - fr,1 - de,2 - en,2- fr,2 - de,3 - en,3 - fr,3 - de\n" +
                "good,bien,,bad,mal,Schlecht,,mal,Schlecht\n";
    }
}
