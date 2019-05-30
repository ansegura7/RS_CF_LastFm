 ======
 README 
 ======

 Version 1.0, May 2010

 . What is this?

    This dataset contains <user, timestamp, artist, song> tuples collected from Last.fm API, 
    using the user.getRecentTracks() method.

    This dataset represents the whole listening habits (till May, 5th 2009) for nearly 1,000 users.

 . Files:

    userid-timestamp-artid-artname-traid-traname.tsv (MD5: 64747b21563e3d2aa95751e0ddc46b68)
    userid-profile.tsv                               (MD5: c53608b6b445db201098c1489ea497df)

 . Data Statistics:

    File userid-timestamp-artid-artname-traid-traname.tsv

      Total Lines:           19,150,868
      Unique Users:                 992
      Artists with MBID:        107,528
      Artists without MBDID:     69,420

 . Data Format:

    The data is formatted one entry per line as follows (tab separated, "\t"):

    userid-timestamp-artid-artname-traid-traname.tsv
      userid \t timestamp \t musicbrainz-artist-id \t artist-name \t musicbrainz-track-id \t track-name

    userid-profile.tsv:
      userid \t gender ('m'|'f'|empty) \t age (int|empty) \t country (str|empty) \t signup (date|empty)

 . Example:

    userid-timestamp-artid-artname-traid-traname.tsv:
      user_000639 \t 2009-04-08T01:57:47Z \t MBID \t The Dogs D'Amour \t MBID \t Fall in Love Again?
      user_000639 \t 2009-04-08T01:53:56Z \t MBID \t The Dogs D'Amour \t MBID \t Wait Until I'm Dead
      ...

    userid-profile.tsv:
      user_000639 \t m \t Mexico \t Apr 27, 2005
      ...

 . License:

    The data contained in lastfm-dataset-1K.tar.gz is distributed with permission of Last.fm. 
    The data is made available for non-commercial use.
    Those interested in using the data or web services in a commercial context should contact: 

    partners [at] last [dot] fm

    For more information see Last.fm terms of service

 . Acknowledgements:

    Thanks to Last.fm for providing the access to this data via their web services. 
    Special thanks to Norman Casagrande.

 . References:

    When using this dataset you must reference the Last.fm webpage.

    Optionally (not mandatory at all!), you can cite Chapter 3 of this book

      @book{Celma:Springer2010,
      	author = {Celma, O.},
      	title = {{Music Recommendation and Discovery in the Long Tail}},
       	publisher = {Springer},
       	year = {2010}
      }

 . Contact:

    This data was collected by Òscar Celma @ MTG/UPF