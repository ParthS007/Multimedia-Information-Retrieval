from collections import Counter
from unidecode import unidecode

def tokenize_title(title, lengths=[2, 3, 4]):
    title = unidecode(title).lower()
    tokens = set()
    for length in lengths:
        tokens.update([title[i:i+length] for i in range(len(title) - length + 1)])
    return tokens

def train_naive_bayes(texts_by_language, top_n):
    models = {}
    for language, texts in texts_by_language.items():
        counter = Counter()
        for text in texts:
            tokens = tokenize_title(text)
            counter.update(tokens)
        # Keep top-n tokens
        most_common = counter.most_common(top_n)
        # Calculate likelihoods
        total = sum(count for _, count in most_common)
        likelihoods = {token: count / total for token, count in most_common}
        models[language] = likelihoods
    return models

def predict_language(text, models):
    tokens = tokenize_title(text)
    scores = {language: sum(models[language].get(token, 0) for token in tokens) for language in models}
    return max(scores, key=scores.get)

texts_by_language = {
    "Spanish": ["Como curry de frijoles rojos con arroz."],
    "French": ["Je mange du curry de haricots rouges avec du riz."],
    "German": ["ich esse kidneybohnen curry mit reis."],
    "Italian": ["Mangio curry di fagioli rossi con riso."]
}
models = train_naive_bayes(texts_by_language, 100)

# Example of prediction
test_text = "Ich esse reis."
predicted_language = predict_language(test_text, models)
print(predicted_language)
