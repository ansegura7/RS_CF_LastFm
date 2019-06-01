# Recommender Systems for Last.fm

## Authors
- Created by Andrés Segura Tinoco
- Created on May 29, 2019

## Program Description
The program has 4 classes. Each of them is described below:

File class: **LastfmRecommender.java**
- Description: Program Main Class of Lastfm Recommender.
- Main methods:
    - **main**: Input function of the main class.
    - **userRecommendation**: Function that makes the recommendation by users.
    - **itemRecommendation**: Function that makes the recommendation by items.
    - **getUserBasedItems**: Returns recommended items using User-based similarity methods.
    - **getItemBasedItems**: Returns recommended items using Item-based similarity methods.

File class: **Utilities.java**
- Description: This class contains static utility functions.
- Main methods:
    - **createDataModel**: Function that create a data model if file exists.
    - **showItemList**: Show the list of recommended items in the console.
    - **saveItemList**: Save into a plain file the list of recommended items.
    - **showMessage**:  Show an audit message in the console.

File class: **LastfmValidation.java**
- Description: Main class to calculate the accuracy of the models.
- Main methods:
    - **main**: Input function of the main class.

File class: **CFRecommenderBuilder.java**
- Description: Collaborative Filtering Recommender builder. Classes container.
- Main classes:
    - **UserJaccardRecommender**: Interface User-based Recommender with Jaccard similarity.
    - **UserCosineRecommender**: Interface User-based Recommender with Cosine similarity.
    - **UserPearsonRecommender**: Interface User-based Recommender with Pearson similarity.
    - **ItemJaccardRecommender**: Interface Item-based Recommender with Jaccard similarity.
    - **ItemCosineRecommender**: Interface Item-based Recommender with Cosine similarity.
    - **ItemPearsonRecommender**: Interface Item-based Recommender with Pearson similarity.
