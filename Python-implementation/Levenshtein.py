def calculate(str1, str2):
    dp = [[0] * (len(str2) + 1) for _ in range(len(str1) + 1)]

    for i in range(len(str1) + 1):
        dp[i][0] = i
    for j in range(len(str2) + 1):
        dp[0][j] = j

    for i in range(1, len(str1) + 1):
        for j in range(1, len(str2) + 1):
            if str1[i - 1] == str2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1]
            else:
                dp[i][j] = min(dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]) + 1

    return dp[-1][-1]

def find_closest_word(target, vocabulary):
    closest_word = ""
    min_distance = float('inf')

    for word in vocabulary:
        distance = calculate(target, word)
        if distance < min_distance:
            min_distance = distance
            closest_word = word

    return closest_word

if __name__ == "__main__":
    target = "kitten"
    vocabulary = ["sitting", "kitchen", "mittens", "biting", "mitten"]
    closest_word = find_closest_word(target, vocabulary)
    
    print("Closest word to '{}' is: {}".format(target, closest_word))
