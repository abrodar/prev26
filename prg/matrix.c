#include <stdio.h>
#include <limits.h>

int memo[11][11]; 
int memoix[11][11]; 

int st_mnozenj(int * sizes, int i, int j) {
	if (i == j) return 0; 
	int min = INT_MAX;
    int minix = i;
	for (int k = i; k < j; k++) {
		int l = st_mnozenj(sizes, i, k); 
		int d = st_mnozenj(sizes, k + 1, j); 
		int st_mnozenj = l + d + sizes[i - 1] * sizes[k] * sizes[j]; 
		if (st_mnozenj < min) {
			min = st_mnozenj; 
			minix = k; 
		}
	}
	memo[i][j] = min; 
	memoix[i][j] = minix; 
	return min; 
}

void oklepaji(int i, int j) {
	if (i == j) {
		printf("A%d", i); 
		return; 
	}
	int min_ix = memoix[i][j]; 
	if (i != min_ix) printf("("); 
	oklepaji(i, min_ix); 
	if (i != min_ix) printf(")"); 
	printf(" x "); 
	if (j != min_ix + 1) printf("("); 
	oklepaji(min_ix + 1, j); 	
	if (j != min_ix + 1) printf(")"); 
}

int main() {
	int n; 
	scanf("%d", &n); 
	int r, c; 
	int sizes[n + 1]; 
	int * sptr = sizes; 
	for (int i = 0; i < n; i++) {
		scanf("%d %d", &r, &c); 
		if (i == 0) {
			*sptr = r; 
			sptr++; 
			*sptr = c; 
		} else {
			*sptr = c; 
		}
		sptr++; 
	}
	printf("min mnozenj: %d\n", st_mnozenj(sizes, 1, n)); 
	printf("("); 
	oklepaji(1, n);
	printf(")"); 	
}
