from unidecode import unidecode
import csv


def tokenize_title(title, lengths=[2, 3, 4]):
    title = unidecode(title).lower()
    tokens = set()
    for length in lengths:
        tokens.update([title[i:i+length] for i in range(len(title) - length + 1)])
    return tokens

file_path = './imdb_top_1000.csv'
tokenized_titles = []
try:
    with open(file_path, newline='', encoding='utf-8') as csvfile:
        csvreader = csv.reader(csvfile)
        next(csvreader)
        for row in csvreader:
            # Extracting 'Series_Title' which is the second column in the CSV
            series_title = row[1]
            tokens = tokenize_title(series_title)
            tokenized_titles.append((series_title, tokens))

    # Displaying the first few tokenized titles to verify the output
    for title, tokens in tokenized_titles[:5]:
        print(f"Series Title: {title}, Tokens: {tokens}")

except FileNotFoundError as e:
    print(f"File not found: {e}")
