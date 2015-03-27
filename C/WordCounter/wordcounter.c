
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct WordInfo {
	char * word;
	int count;
};

typedef struct WordInfo WordInfo;

int maxWords;
int nWords;
WordInfo*  wordArray;

#define MAXWORD 1000
int wordLength;
char word[MAXWORD];
FILE * fd;
int charCount;
int wordPos;

void toLower(char *s) {
	while(*s){
	*s = tolower(*s);
	s++;

}
return;
}

// It returns the next word from stdin.
// If there are no more more words it returns NULL. 
static char * nextword() {
	int c;
	char* rtword = &word[0];
	int i = 0;
	int status = 0;
	while ((c=getc(fd))!=-1){
	status = 0;
	word[i] = c;
	if(c == ' ' || c == '\n' || c == '\t' || c == '\r'){
	if (wordLength > 0){
		wordLength = 0;
		word[i] = '\0';
		i = 0;	
		return rtword;
		}
	wordLength = 0;
	i=0;
	status = 1;
	}
	if (status == 0){
	wordLength++;
	i++;
	}
	}
}

int
main(int argc, char **argv)
{
	if (argc < 2) {
		printf("Usage: countwords filename\n");
		exit(1);
	}

	char * filename = argv[1];

	fd = fopen(filename, "r");	
	char words[100][30];
	char temp[30];
	int count[100];
	int length = 1;
	int i =0;
	int j;
	int status,totalwords = 0;
	while((fscanf(fd,"%s", temp)) != EOF){
		
		for(j = 0; temp[j]; j++){  
		temp[j] = tolower(temp[j]);
		if(temp[j] == '.'){
			temp[j] = '\0';
		}
		}
		
		status = 0;
		for(j =0; j< length; j++){
			if(strcmp(temp,words[j])==0){
				count[j]++;
				status = 1;
			}
		}
			if(status == 0){
				strcpy(words[length-1],temp);
				count[length-1] = 1;
				length++;
			}
			totalwords++;
		}
		
		int x;


		
		int min = 200;
		int start,pos,tempn = 0;
		int max = 1;
		char * tempw = malloc(1000);
		
		
		start = 0;
		while(start < (length-1)){
			min = 200;
			status = 0;
			for(x=start; x<(length-1); x++){
				if(words[x][0] < min){
					min = words[x][0];
					pos = x;
					status = 1;
			}
			}
			if (status == 1){
			strcpy(tempw,words[start]); //Copy current position to temp
			tempn = count[start];
					strcpy(words[start],words[pos]); //Replace lowest position
					count[start] = count[pos];
					strcpy(words[pos],tempw);
					count[pos] = tempn; 
				
			}
			start++;
		}

	

	for(x=0; x < (length-1); x++){ 
	printf("%s %d\n", words[x], count[x]);
	}
	return 0;
}

