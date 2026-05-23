import pandas as pd
import numpy as np

# Step 1: Load the dataset
df_rain = pd.read_csv('/home/sandip-kumar-shah/Desktop/dwdm/rrn1.csv')
print("Original Dataset:")
print(df_rain)

# Normalize column types (handle 'NAN'/'NaN' strings and coerce to numeric)
df_rain['max.temp'] = pd.to_numeric(df_rain['max.temp'], errors='coerce')
df_rain['min.atmos.pressure'] = pd.to_numeric(df_rain['min.atmos.pressure'], errors='coerce')

# a. Descriptive Statistics
print("\n=== Descriptive Statistics ===")
print(df_rain.describe())

# b. Mean and Median of max.temp and min.atmos.pressure
print("\n=== Mean and Median ===")
print("Max Temperature - Mean:", df_rain['max.temp'].mean())
print("Max Temperature - Median:", df_rain['max.temp'].median())
print("Min Atmos Pressure - Mean:", df_rain['min.atmos.pressure'].mean())
print("Min Atmos Pressure - Median:", df_rain['min.atmos.pressure'].median())

# c. Handle Missing Values (Mean Imputation)
# Fill missing values with mean
df_rain['max.temp'] = df_rain['max.temp'].fillna(df_rain['max.temp'].mean())
df_rain['min.atmos.pressure'] = df_rain['min.atmos.pressure'].fillna(df_rain['min.atmos.pressure'].mean())

print("\n=== Cleaned Dataset ===")
print(df_rain)
