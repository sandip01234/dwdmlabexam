import pandas as pd
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import MultinomialNB
from sklearn.metrics import accuracy_score, classification_report

# Load dataset
df_gender = pd.read_csv('/home/sandip-kumar-shah/Desktop/dwdm/gender_prdct.csv')
print("Original Data:")
print(df_gender)

# Data Cleaning - Check missing
print("\nMissing Values:")
print(df_gender.isnull().sum())

# Feature Encoding
X = pd.get_dummies(df_gender[['Occupation', 'Eye Color', 'Nationality']], drop_first=True)
y = df_gender['Gender']

# Label encode target
le = LabelEncoder()
y_encoded = le.fit_transform(y)

# Train-Test Split
X_train, X_test, y_train, y_test = train_test_split(X, y_encoded, test_size=0.3, random_state=42)

# Multinomial Naive Bayes
nb_model = MultinomialNB()
nb_model.fit(X_train, y_train)

# Prediction
y_pred = nb_model.predict(X_test)

# Accuracy
accuracy = accuracy_score(y_test, y_pred)
print(f"\nModel Accuracy: {accuracy:.2%}")

print("\nClassification Report:")
print(classification_report(y_test, y_pred, target_names=le.classes_))

# Predict for new case: [actuary, green, japan]
new_case = pd.DataFrame({
    'Occupation': ['actuary'],
    'Eye Color': ['green'],
    'Nationality': ['japan']
})

new_case_encoded = pd.get_dummies(new_case)
new_case_encoded = new_case_encoded.reindex(columns=X.columns, fill_value=0)

pred = nb_model.predict(new_case_encoded)
predicted_gender = le.inverse_transform(pred)
print(f"\nPredicted Gender for [actuary, green, Japan]: {predicted_gender[0]}")