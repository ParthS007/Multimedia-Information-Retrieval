import csv
from sentence_transformers import SentenceTransformer, util

model = SentenceTransformer('all-MiniLM-L6-v2')

def encode_titles(titles):
    return [model.encode(title) for title in titles]

def semantic_search(query, encoded_titles, titles):
    query_vector = model.encode(query)
    scores = [float(util.dot_score(query_vector, title_vec)) for title_vec in encoded_titles]
    
    # Pairing titles with their scores and sorting
    scored_titles = sorted(zip(titles, scores), key=lambda x: x[1], reverse=True)
    return scored_titles

# Load and process the CSV file
file_path = './imdb_top_1000.csv'

titles = []
try:
    with open(file_path, newline='', encoding='utf-8') as csvfile:
        csvreader = csv.reader(csvfile)
        next(csvreader)
        for row in csvreader:
            # Extracting 'Series_Title' which is the second column in the CSV
            series_title = row[1]
            titles.append(series_title)

    # Encode the titles
    encoded_titles = encode_titles(titles)

    # Example semantic search
    search_query = "crime family"
    search_results = semantic_search(search_query, encoded_titles, titles)
    print(search_results[:5])  # Print top 5 results

except FileNotFoundError as e:
    print(f"File not found: {e}")
