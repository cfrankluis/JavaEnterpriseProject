CREATE TABLE questions(
	question_id INTEGER PRIMARY KEY,
	question TEXT
);

CREATE TABLE accounts(
	account_id SERIAL PRIMARY KEY,
	firstname TEXT NOT NULL,
	lastname TEXT NOT NULL,
	email TEXT NOT NULL,
	"password" TEXT NOT NULL,
	date_of_birth DATE NOT NULL,
	aboutme TEXT,
	UNIQUE (email)
);

CREATE TABLE answers(
	user_fk INTEGER,
	question_fk INTEGER,
	answer TEXT,
	CONSTRAINT answer_key PRIMARY KEY (user_fk, question_fk),
	FOREIGN KEY (user_fk) REFERENCES accounts(account_id),
	FOREIGN KEY (question_fk) REFERENCES questions(question_id)
);

--SEED DATA FOR SECURITY QUESTIONS
INSERT INTO questions VALUES (1,'What is your mothers''s maiden name?');
INSERT INTO questions VALUES (2,'What was your childhod nickname?');
INSERT INTO questions VALUES (3,'What is the name of your best friend?');

--SEED DATA FOR USER ACCOUNTS
INSERT INTO accounts VALUES (1,'Spongebob', 'Squarepants', 'dishes@mysink.com','gary', '2003-06-1','I live in pineapple under the sea');
INSERT INTO accounts VALUES (2,'Eugene', 'Krabs', 'crustacean@atlantis.com','krabbypatty', '2003-06-1','I would like everyone to give me their money');


--SEED DATA FOR SECURITY QUESTION ANSWERS
INSERT INTO answers VALUES (1,3,'patrick star');
INSERT INTO answers VALUES (2,2,'I do not know');