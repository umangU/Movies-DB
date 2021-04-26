/* customer Table*/
CREATE TABLE Customers (
  customer_id   INTEGER PRIMARY KEY,
  last_name     VARCHAR(50) NOT NULL, 
  first_name    VARCHAR(50) NOT NULL,
  address       VARCHAR(200),
  city          VARCHAR(50),
  state      VARCHAR(3) CHECK(state IN('NSW','VIC','QLD','ACT','TAS','NT','SA','WA')), 
  postcode   VARCHAR(8)
  );

/* movies Table*/                              
CREATE TABLE Movies (
  movie_id     INTEGER PRIMARY KEY,
  movie_title  VARCHAR(100) NOT NULL,
  director_last_name  VARCHAR(50) NOT NULL,
  director_first_name VARCHAR(50) NOT NULL,
  genre      VARCHAR(20) CHECK(genre IN('Action','Adventure','Comedy','Romance','Science Fiction','Documentary','Drama','Horror')),
  media_type VARCHAR(20) CHECK(media_type IN('DVD','Blu-Ray')),   
  release_date DATE,
  studio_name VARCHAR(50),
  retail_price REAL CHECK(retail_price > 0),
  current_stock REAL CHECK(current_stock >= 0)
);


CREATE TABLE Shipments (
  shipment_id INTEGER PRIMARY KEY,
  customer_id  INTEGER NOT NULL,
  movie_id   INTEGER NOT NULL,
  shipment_date  DATE,
  FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
  FOREIGN KEY (movie_id) REFERENCES Movies(movie_id)
);
