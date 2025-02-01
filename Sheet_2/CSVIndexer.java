import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class CSVIndexer {

    public static void main(String[] args) throws IOException {
        String indexPath = "./";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to index from CSV? (yes/no)");
        String indexChoice = scanner.nextLine();

        if (indexChoice.equalsIgnoreCase("yes")) {
            if (!isIndexExists(indexPath)) {
                System.out.println("Provide the path to csv");
                String csvFilePath = scanner.nextLine();
                indexFromCSV(csvFilePath, indexPath);
            } else {
                System.out.println("Index already exists. Skipping indexing.");
            }
        }

        System.out.println("Do you want to search? (yes/no)");
        String searchChoice = scanner.nextLine();

        if (searchChoice.equalsIgnoreCase("yes")) {
            System.out.println("Enter the search query:");
            String queryStr = scanner.nextLine();
            MovieSearch.performSearch(queryStr, indexPath);
        }
    }

    public static boolean isIndexExists(String indexPath) {
        Path indexDirectory = Paths.get(indexPath);
        Path segmentsFile = indexDirectory.resolve("segments.gen");

        boolean exists = Files.exists(segmentsFile);

        if (exists) {
            try {
                BasicFileAttributes attrs = Files.readAttributes(segmentsFile, BasicFileAttributes.class);
                return attrs.isRegularFile(); // Check if it's a regular file
            } catch (IOException e) {
                return false;
            }
        }

        return false;
    }


    public static void indexFromCSV(String csvFilePath, String indexPath) {
        try {
            Directory directory = FSDirectory.open(Paths.get(indexPath));
            StandardAnalyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, config);

            Reader reader = new FileReader(csvFilePath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

            for (CSVRecord csvRecord : csvParser) {
                String seriesTitle = csvRecord.get("Series_Title");
                String overview = csvRecord.get("Overview");
                float imdbRating = Float.parseFloat(csvRecord.get("IMDB_Rating"));
                int releasedYear = Integer.parseInt(csvRecord.get("Released_Year"));


                Document doc = new Document();
                doc.add(new org.apache.lucene.document.TextField("title", seriesTitle, org.apache.lucene.document.Field.Store.YES));
                doc.add(new org.apache.lucene.document.TextField("description", overview, org.apache.lucene.document.Field.Store.YES));
                doc.add(new FloatPoint("rating", imdbRating));
                doc.add(new StoredField("storedRating", imdbRating));


                doc.add(new IntPoint("rating", releasedYear));
                doc.add(new StoredField("storedReleasedYear", releasedYear));

                indexWriter.addDocument(doc);
            }


            indexWriter.close();
            csvParser.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
