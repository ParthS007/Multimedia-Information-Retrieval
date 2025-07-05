# Multimedia Information Retrieval

_This repository contains code implementations for understanding information retrieval practically and applying NLP techniques._

## 📚 Table of Contents

- [Overview](#overview)
- [Course Structure](#course-structure)
- [Technologies Used](#technologies-used)
- [Exercises](#exercises)
  - [Sheet 0: Text Retrieval Fundamentals](#sheet-0-text-retrieval-fundamentals)
  - [Sheet 1: Video Shot Detection Performance](#sheet-1-video-shot-detection-performance)
  - [Sheet 2: Lucene-based Movie Search](#sheet-2-lucene-based-movie-search)
  - [Sheet 3: Language Detection & Subword Tokenization](#sheet-3-language-detection--subword-tokenization)
  - [Sheet 4: Semantic Search with Sentence Transformers](#sheet-4-semantic-search-with-sentence-transformers)
  - [Sheet 5: Image Processing & Skin Detection](#sheet-5-image-processing--skin-detection)
- [Setup & Installation](#setup--installation)
- [Usage](#usage)
- [License](#license)

## 🎯 Overview

This repository is a comprehensive collection of practical exercises covering various aspects of **Multimedia Information Retrieval (MIR)**. The projects span across text retrieval, natural language processing, computer vision, and machine learning techniques applied to multimedia data.

The exercises demonstrate both **classical** and **modern** approaches to information retrieval, including:
- Traditional text indexing and search (Apache Lucene)
- Video shot boundary detection
- Language detection algorithms
- Semantic search using transformer models
- Image processing and classification

## 📖 Course Structure

The course is structured into 6 main exercise sheets (Sheet 0-5), each focusing on different aspects of multimedia information retrieval:

## 🛠 Technologies Used

- **Languages**: Python, Java
- **Libraries & Frameworks**:
  - **Python**: pandas, scikit-learn, matplotlib, seaborn, opencv-python, nltk, sentence-transformers, numpy
  - **Java**: Apache Lucene, Apache Commons CSV
- **Tools**: Jupyter Notebooks, BeautifulSoup, PIL (Python Imaging Library)
- **Datasets**: IMDB movies, SimpleWiki, language detection datasets, skin detection datasets

## 📋 Exercises

### Sheet 0: Text Retrieval Fundamentals

**Topics**: Classical text retrieval with Apache Lucene

**Files**:
- `classical_text_retrieval.ipynb` - Jupyter notebook with Lucene implementation
- `Advanced Text Retrieval-NLP.pdf` - Theoretical background
- `Classical Text Retrieval.pdf` - Course material
- `Performance of Video Shot Detection.ipynb` - Video analysis implementation
- `nlp_*.py` - Python scripts for NLP tasks

**Key Concepts**:
- Document indexing and retrieval
- Boolean queries and scoring
- Text analysis and tokenization
- Search optimization techniques

### Sheet 1: Video Shot Detection Performance

**Topics**: Performance evaluation of video shot boundary detection algorithms

**Files**:
- `exercise_1.ipynb` - Main implementation notebook
- `Exercise 1 - Improved.txt` - Improved algorithm results
- `Exercise 1 - Naive.txt` - Naive algorithm results

**Key Concepts**:
- **Confusion Matrix Analysis**: Calculating TP, TN, FP, FN for shot detection
- **ROC Curve Analysis**: Plotting and interpreting receiver operating characteristics
- **Performance Metrics**: Sensitivity, specificity, accuracy, AUC calculations
- **Threshold Optimization**: Finding optimal decision thresholds
- **Comparative Analysis**: Naive vs improved detection algorithms

### Sheet 2: Lucene-based Movie Search

**Topics**: Building a search engine for movie data using Apache Lucene

**Files**:
- `CSVIndexer.java` - Lucene indexing implementation
- `MovieSearch.java` - Search functionality
- `imdb_top_1000.csv` - Movie dataset
- `exercise_2.zip` - Complete project archive

**Key Concepts**:
- CSV data indexing
- Fuzzy search implementation
- Boolean query construction
- Document scoring and ranking

### Sheet 3: Language Detection & Subword Tokenization

**Topics**: Text analysis, language detection, and similarity matching

**Files**:
- `exercise_3.ipynb` - Main implementation
- `imdb.csv` - Movie titles dataset
- `ld.csv` - Language detection dataset

**Key Concepts**:
- **Language Detection**: Using stopwords and NLTK for language identification
- **Subword Tokenization**: N-gram based text analysis (2-grams, 3-grams, 4-grams)
- **Similarity Calculation**: Jaccard similarity for text matching
- **Semantic Search**: Using transformer models (SentenceTransformers)
- **Performance Benchmarking**: Comparing different embedding models

### Sheet 4: Semantic Search with Sentence Transformers

**Topics**: Advanced semantic search using modern NLP techniques

**Files**:
- `exercise_4.ipynb` - Semantic search implementation
- `data/simplewiki-2020-11-01.jsonl.gz` - Wikipedia dataset

**Key Concepts**:
- **Data Processing**: Parsing compressed JSON datasets
- **Sentence Embeddings**: Using pre-trained transformer models
- **Semantic Similarity**: Cosine similarity for sentence matching
- **Context Expansion**: Enhancing search results with neighboring content
- **Question Answering**: Building QA systems with context retrieval

### Sheet 5: Image Processing & Skin Detection

**Topics**: Computer vision and image classification

**Files**:
- `exercise_5.ipynb` - Image processing implementation
- `face.jpg`, `MK.jpg` - Sample images
- `result.png` - Processing results
- `Skin_NonSkin.txt` - Skin detection dataset
- `skin_dataset/` - Image classification dataset

**Key Concepts**:
- **Color Space Conversion**: BGR to XYZ transformations
- **Principal Component Analysis (PCA)**: Dimensionality reduction for visualization
- **Decision Tree Classification**: Machine learning for skin detection
- **Image Preprocessing**: Resizing, normalization, and feature extraction
- **Performance Evaluation**: Classification accuracy and model assessment

## 🚀 Setup & Installation

### Prerequisites

- **Python 3.8+**
- **Java 11+**
- **Jupyter Notebook**

### Python Dependencies

```bash
pip install pandas numpy matplotlib seaborn scikit-learn opencv-python nltk sentence-transformers pillow unidecode tqdm
```

### Java Dependencies

For Lucene-based exercises, ensure you have:
- Apache Lucene 9.7.0
- Apache Commons CSV

### NLTK Data

Download required NLTK data:
```python
import nltk
nltk.download('stopwords')
nltk.download('punkt')
nltk.download('punkt_tab')
```

## 💻 Usage

### Running Jupyter Notebooks

```bash
# Navigate to the repository
cd Multimedia-Information-Retrieval

# Start Jupyter Notebook
jupyter notebook

# Open any .ipynb file to run the exercises
```

### Running Java Applications

```bash
# Navigate to Sheet_2
cd Sheet_2

# Compile Java files
javac -cp ".:lib/*" *.java

# Run the indexer
java -cp ".:lib/*" CSVIndexer
```

### Example Usage

1. **Video Shot Detection Analysis** (Sheet 1):
   ```python
   # Load and analyze shot detection performance
   python -c "import pandas as pd; data = pd.read_csv('Sheet_1/Exercise 1 - Improved.txt', delimiter='\t'); print(data.head())"
   ```

2. **Movie Search** (Sheet 2):
   ```bash
   # Index movies and perform search
   cd Sheet_2
   java CSVIndexer
   # Follow interactive prompts
   ```

3. **Language Detection** (Sheet 3):
   ```python
   # Detect language from text
   from Sheet_3.exercise_3 import detect_language
   result = detect_language("Hola mundo", ["english", "spanish", "french"])
   ```

## 📊 Key Results & Insights

- **ROC Analysis**: Improved shot detection algorithm achieved AUC of 0.9711 vs 0.9420 for naive approach
- **Semantic Search**: Sentence transformers significantly outperform traditional n-gram approaches
- **Language Detection**: Stopword-based detection achieves high accuracy for European languages
- **Image Classification**: PCA visualization reveals clear clustering of skin vs non-skin samples

## 🤝 Contributing

This repository is primarily for educational purposes. If you find issues or have improvements:

1. Fork the repository
2. Create a feature branch
3. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Note**: This repository contains coursework for academic purposes. Datasets and some implementations may require appropriate citations when used in research or commercial applications.
