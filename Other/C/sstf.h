#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

struct SSTFSetting {
    char* Name;
    char* Data;
};

char* getSubstring(const char* str, int start, int end) {
    // Calculate the length of the substring
    int substringLength = end - start;

    // Allocate memory for the substring buffer
    char *substring = (char*)malloc(substringLength + 1); // +1 for the null terminator
    if (substring == NULL) {
        //Memory Alloc fail
        return NULL;
    }

    // Copy characters from the original string to the substring buffer
    strncpy(substring, str + start, substringLength);
    substring[substringLength] = '\0'; // Null-terminate the substring

    return substring;
}

char* trimWhitespace(const char* str) {
    // Find the first non-whitespace character from the start of the string
    while (isspace(*str)) {
        str++;
    }

    // If the entire string is whitespace, return an empty string
    if (*str == '\0') {
        return strdup("");
    }

    // Find the last non-whitespace character from the end of the string
    const char* end = str + strlen(str) - 1;
    while (end > str && isspace(*end)) {
        end--;
    }

    // Calculate the length of the trimmed string
    size_t length = end - str + 1;

    // Allocate memory for the trimmed string buffer
    char* trimmed = (char*)malloc(length + 1); // +1 for the null terminator
    if (trimmed == NULL) {
        perror("Memory allocation failed");
        return NULL;
    }

    // Copy the trimmed string to the new buffer
    strncpy(trimmed, str, length);
    trimmed[length] = '\0'; // Null-terminate the string

    return trimmed;
}


inline void WriteSSFTSettings(const char* Path, struct SSTFSetting *settings, int size){
    if(settings == NULL || Path == NULL) return;
    if(size < 1) return;

    FILE *file = fopen(Path, "w");
    for(int i = 0; i < size; i++) {
        fprintf(file, strcat(strcat(strcat(settings[i].Name, "│"), settings[i].Data), "\n"), i+1);
    }

    fclose(file);
    return;
}

inline struct SSTFSetting* ParseSSFTSettings(const char* Path) {

    long int lines = 0;
    FILE *file = fopen(Path, "r");

    if(file == NULL) return NULL;

    while (EOF != (fscanf(file, "%*[^\n]"), fscanf(file,"%*c")))
       ++lines;

    if(lines < 1) return NULL;

    struct SSTFSetting *Settings = (struct SSTFSetting*)malloc(lines * sizeof(struct SSTFSetting));

    char line[256];

    for (int i = 0; i < lines; i++) {
        if (fgets(line, sizeof(line), file) != NULL) {
            int location = (int)strcspn(line, "│");
            char* nam = getSubstring(line, 0, location);
            char* dat = trimWhitespace(getSubstring(line, location, 256));
            struct SSTFSetting setting = {nam, dat};
            Settings[i] = setting;
        } else {
            break;
        }
    }

    return Settings;
}