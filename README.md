# Recommender Systems for Last.FM
Recommendation system with collaborative filtering created with Apache Mahout. The system uses a Music Recommendation Dataset for Research as input, but you can train with any other dataset.

## Run
A .JAR file of the program was created. The JAR name is: RS_CF_LastFm-v1.jar and you must send as input parameters:
- The ID of the user to whom you want to give recommendations
- The filepath to the input data
- The filepath to the output file
- The type of collaborative filtering
- The similarity metric
- The number of neighbors for the KNN algorithm (only applies with user-based filtering)
- The number of recommendations

Execution examples:
```
    java -jar RS_CF_LastFm-v1.jar USER_ID INPUT_FILE OUTPUT_FILE [USER|ITEM] [COSINE|PEARSON|JACCARD] K_NN N_RECOMMENDATIONS

    java -jar RS_CF_LastFm-v1.jar 1 ../data/in/u.data.csv ../data/out/output.txt USER COSINE 101 20
    java -jar RS_CF_LastFm-v1.jar 10 ../data/in/u.data.csv ../data/out/output.txt ITEM PEARSON 0 10
```

The .JAR program must be run with Java 7 or higher.

## Data
The original dataset contains <user, timestamp, artist, song> tuples collected from Last.fm API, using the user.getRecentTracks() method. This dataset represents the whole listening habits (till May, 5th 2009) for nearly 1,000 users.

This pre-processed dataset contains <user, song, rating> tuples from the number of times a song was heard on Last.fm.

Table format: **u.data.csv**

| user id | item id | rating
| -- | -- | -- |
| 1 | 100001 | 5.0 |
| 3 | 101943 | 4.6 |
| 6 | 100906 | 4.3 |
| 11 | 101722 | 3.6 |
| 15 | 107070 | 3.9 |

Permalink: https://www.dtic.upf.edu/~ocelma/MusicRecommendationDataset/lastfm-1K.html

## Contributing and Feedback
Any kind of feedback/criticism would be greatly appreciated (algorithm design, documentation, improvement ideas, spelling mistakes, etc...).

## Authors
- Created by Andrés Segura Tinoco
- Created on May 29, 2019

## License
This project is licensed under the terms of the MIT license.

## Acknowledgements:
Thanks to Last.fm for providing the access to this data via their web services.
