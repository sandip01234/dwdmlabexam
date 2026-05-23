import pandas as pd
import numpy as np
from sklearn.preprocessing import LabelEncoder, StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC
from sklearn.metrics import confusion_matrix, classification_report

# Load dataset
df_cancer = pd.read_csv('/home/sandip-kumar-shah/Desktop/dwdm/breast_cncr_prdctn1.csv')
print("Dataset Shape:", df_cancer.shape)

# a. Identify missing values
print("\nMissing Values per Column:")
print(df_cancer.isnull().sum())

# b. Total cases of each group
print("\nTotal Cases:")
print(df_cancer['diagnosis'].value_counts())

# c. Label Encoding
le = LabelEncoder()
df_cancer['diagnosis'] = le.fit_transform(df_cancer['diagnosis'])  # M=1, B=0

# d. Standard Scaler
X = df_cancer.drop('diagnosis', axis=1)
y = df_cancer['diagnosis']

scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

# Train-Test Split
X_train, X_test, y_train, y_test = train_test_split(X_scaled, y, test_size=0.2, random_state=42)

# e. SVM Model
svm_model = SVC(kernel='linear', random_state=42)
svm_model.fit(X_train, y_train)

# Predictions
y_pred = svm_model.predict(X_test)

# f. Confusion Matrix
print("\n=== Confusion Matrix ===")
cm = confusion_matrix(y_test, y_pred)
print(cm)

print("\n=== Classification Report ===")
print(classification_report(y_test, y_pred, target_names=['Benign (B)', 'Malignant (M)']))