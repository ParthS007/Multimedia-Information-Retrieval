import nltk
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize

nltk.download('stopwords')
nltk.download('punkt')

def detect_language(text):
    languages = ['english', 'german', 'italian']
    stop_words = {lang: set(stopwords.words(lang)) for lang in languages}
    words = set(word_tokenize(text))

    lang_stopword_count = {lang: len(words & stop_words[lang]) for lang in languages}
    return max(lang_stopword_count, key=lang_stopword_count.get)

# Example usage
text = "ich esse kidneybohnen curry mit reis."
print(detect_language(text))

text = "i eat kidney bean curry with rice."
print(detect_language(text))

text = "mangio fagioli rossi curry con riso."
print(detect_language(text))
