import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class MovieSearch {

    public static void performSearch(String keywords, String indexPath) {
        try {
            Directory directory = FSDirectory.open(Paths.get(indexPath));
            IndexReader indexReader = DirectoryReader.open(directory);


            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            // Create a FuzzyQuery for the title field based on Levenshtein distance
            Query titleQuery = new FuzzyQuery(new Term("title", keywords), 2); // Adjust the fuzziness threshold as needed
            // Create a TermQuery for the description field
            Query descriptionQuery = new TermQuery(new Term("description", keywords));


            // Combine queries using BooleanQuery to search for both title and description
            BooleanQuery.Builder booleanQueryBuilder = new BooleanQuery.Builder();
            booleanQueryBuilder.add(titleQuery, BooleanClause.Occur.SHOULD);
            booleanQueryBuilder.add(descriptionQuery, BooleanClause.Occur.SHOULD);

            Query finalQuery = booleanQueryBuilder.build();

            int hitsPerPage = 5;

            TopDocs topDocs = indexSearcher.search(finalQuery, hitsPerPage);
            ScoreDoc[] hits = topDocs.scoreDocs;

            for (ScoreDoc hit : hits) {
                try {
                    Document doc = indexSearcher.doc(hit.doc);
                    System.out.println("Title: " + doc.get("title"));
                    System.out.println("Description: " + doc.get("description"));
                    System.out.println("Rating: " + doc.get("storedRating"));
                    System.out.println("Release Date: " + doc.get("storedReleasedYear"));
                    System.out.println("----------");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            indexReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}