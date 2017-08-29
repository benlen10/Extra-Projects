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

void decrypt(char *s)
{
    int i, l = strlen(s);
    for(i = 0; i < l; i++)
        s[i] += num;
}





main(){
	char * str = (char*) malloc(100);
	printf("Type your option and press enter:\n");
	printf("ENCRYPT or DECRYPT\n");
	scanf("%s", str);
	if(strcmp(str,"ENCRYPT")==0){
		printf("Type a string to encrypt. Press ENTER to confirm\n");
	scanf("%s", str);
	printf("Type an encryption number\n");
	scanf("%d",&num);
	encrypt(str);
	printf("Encrypted String: %s\n",str);
	return 0;
	}
	else if(strcmp(str,"DECRYPT")==0){
		printf("Type a string to decrypt. Press ENTER to confirm\n");
	scanf("%s", str);
	printf("Type an decryption number\n");
	scanf("%d",&num);
	decrypt(str);
	printf("Decrypted String: %s\n",str);
	}
	else{
		printf("Invalid option\n");
		return 1;
	}
	return 0;
}
	
	
	
	