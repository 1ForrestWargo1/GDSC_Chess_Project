from flask import Flask

app = Flask(__name__)

@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"

if __name__ == "__main__":
	app.run(debug=True)    
    
    
    




'''
1. Frontend to Backend using URL 
    a. Get a board object (json)

2. Backend to AI
    a. Process(?) the json board 
    b. Send to AI & Chess code 

3. Backend to Frontend
    a. Send a board object (JSON) with the new move
    b. JSON format: set of set of 8, with 0 = empty, number = piece, + white - black

'''