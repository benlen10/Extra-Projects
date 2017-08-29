
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Adds src at the end of dest. Return the value of dest.
char *mystrcat(char * dest, char * src) {

char * s = malloc(1000);
s = src;
char * d = malloc(1000);
d = dest;

while(*d){
d++;
}

while((*s)){
*d=*s;
d++;
s++;
}

*d='\0';
return dest;
}


char * mystrstr(char * haystack, char * needle) {
char * p = malloc(100);
p=haystack;
char * q = malloc(100);
q=needle;
char * last = malloc(100);
*last = NULL;
	while( *p!='\0' ){
		last=p;
		while(*p==*q){
			if(*(q+1)=='\0'){
				return last;
			}
			q++;
			p++;
		}
		p = last;
		p++; 
		q=needle; 
	}
	return NULL;
}


// Trims any spaces that are at the beginning or the end of s and returns s.
// Use the same memory passed in s. 
// Example:
// char s1[20];
// strcpy(s1,      hello    );
// strtrim(s1);
// printf(<s1=%s>\n, s1); // it will print <s1=hello>  without the spaces.
char * mystrtrim(char * s) {
char * str = s;
		while(*str == ' '){
		str++;
		}


	while(*str!=' '){
		*s=*str;
		s++;
		str++;
		}
		*s='\0';

  return s;
}


// Returns a new string that will substitute the first occurrence of the substring from
//  to the substring to in the string src
// This call will allocate memory for the new string and it will return the new string.
// Example:
// char a[6];
// strcpy(a, apple);
// char * b = strreplace(a, pp, orange);
// printf(<b=%s>\n, b); // It will print <b=aorangele>  
char * mystrreplace( char * src, char * from, char * to)  {
char * new = malloc(1000);
char * temp = NULL;
char * origNew = new;
char * origFrom = from;
char * origTo = to;
int status = 0;
while(*src != '\0'){
	status = 0;
	temp = src;
	while(*src==*from){
		src++;
		from++;
		if(*(src+1) == '\0'){
			src--;
			new++;
			*new = *src;
			new++;
			*new = '\0';
			return origNew;
		}
	if(*from == '\0'){
		status = 1;
		while(*to){
			*new=*to;
			new++;
			to++;
		}
		to = origTo;
	}
	
	}
	
	if(status == 0){
		src = temp;
	}
	from = origFrom;
	if(status==0){
	*new=*src;
	new++;
	src++;
	}
	
}
*new = '\0';
return origNew;
}

main(){
	printf("Custom String Functions.");
}



