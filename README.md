# todo

A Clojure library designed to ... well, that part is up to you.

## Usage

### Create 
    curl -X POST http://localhost:9990/task -H "Content-Type: application/json" -d '{"title": "Clojure", "description": "Create a new API"}'

### Find all
    curl -X GET http://localhost:9990/task

### Find one
    curl -X GET http://localhost:9990/task/03bf2267-df1d-4f38-9e53-24192ba5b712