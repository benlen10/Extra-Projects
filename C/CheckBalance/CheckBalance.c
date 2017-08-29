#include <stdio.h>
#define MAXLENGTH 100

main(int argc, char * argv[]){
	if (argc !=2){
		printf("Invalid Arg Count");
		return 1;
	}
	
	char * str = malloc(MAXLENGTH);
	strcpy(str,argv[1]);
	int par = 0;
	int square = 0;
	int curl = 0;
	while(*str){
		if (*str == '{'){
			curl++;
		}
		else if(*str == '['){
			square++;
		}
		else if(*str == '('){
			par++;
		}
		else if(*str == ']'){
			square--;
		}
		else if(*str == '}'){
			curl--;
		}
		else if(*str == ')'){
			par--;
		}
		str++;
	}
	if((curl == 0) && (par ==0) && (square==0)){
		printf("String is balanced");
		return 0;
	}
	else{
		printf("String is not balanced");
		return 0;
	}
}
