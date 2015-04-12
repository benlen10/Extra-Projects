//A simple program to encrypt text strings by shifting the char values
# include <stdio.h>
# include <string.h>
#include <stdlib.h>

int num = 0;

void encrypt(char *s)
{
    int i, l = strlen(s);
    for(i = 0; i < l; i++)
        s[i] -= num;
}

main(){
	printf("Type a string to encrypt. Press ENTER to confirm\n");
	char * str = (char*) malloc(100);
	
	scanf("%s", str);
	printf("Type an encryption number\n");
	scanf("%d",&num);
	encrypt(str);
	printf("Encrypted String: %s\n",str);
}
	
	
	