## Overview

An automated test for a couple of pages on the [Yandex.Market](https://market.yandex.ru) site.


## Details

This test performs the following steps:

1. Open https://market.yandex.ru
2. Select the category: _Computers_ -> _Tablets_
3. Select the manufacturer: _Samsung_
4. Sort by price
5. Output the top 5 results to the log
6. Remember the second result (name and price)
7. Type the name into the search field and click the search button
8. Check that the first search result is the same as the one saved on step 6


## Prerequisites

Requires Chrome browser to be installed.


## Running

Starting (with Maven):
```
mvn verify
```
