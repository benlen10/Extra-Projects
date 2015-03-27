
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

#define MAXWORD 2000
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


static char * nextword() {
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
	char words[500][30];
	char temp[500];
	int count[500];
	int length = 1;
	int i =0;
	int j;
	char c;
	int stat = 0;
	int status,totalwords = 0;
		do{
		c = fgetc(fd);
		c = tolower(c);
			stat = 0;
		if (((c>= 'a') && (c<= 'z'))||((c>= 'A') && (c<= 'Z'))){
			while(((c>= 'a') && (c<= 'z'))||((c>= 'A') && (c<= 'Z'))){  //Step through rest of the word
			temp[j] = c;
			j++;
			c = tolower(fgetc(fd));
			}
			temp[j] = '\0';    // Place Null char at end of word
			
			for(j =0; j< length; j++){  
			if(strcmp(temp,words[j])==0){   //Check to see if word already exists
				count[j]++;
				totalwords++;
				status = 1;
			}
		}
			if(status == 0){                  //If it does not exist, add to list
				strcpy(words[length-1],temp);
				count[length-1] = 1;
				length++;
				totalwords++;
			}
		}

			status = 0;	
			j = 0;
		}while(c != EOF);
		
		close(fd);
		int x;

		int tmp = 0;
	
		char * tempa = malloc(1000);
		while(1){
			
			status = 0;
			for(x=0; x<(length); x++){
				if((strcmp(words[x],words[x+1]) > 0)){
					tempa = malloc(1000);
					tmp = 0;
					strcpy(tempa,words[x]);
					tmp = count[x];
					strcpy(words[x],words[x+1]);
					count[x] = count[x+1];
					strcpy(words[x+1],tempa);
					count[x+1] = tmp;
					status = 1;
				}
			}
			if(status == 0){
				
				int start;
				if(length >50){
					start = 2;
				}
				else{
					start = 2;
				}

	
	for(x=start; x <= (length); x++){ 
	printf("%s %d\n", words[x], count[x]);
	}
	
	return 0;
			}
		}
		printf("DONE");
		return 0;
		
		
}
