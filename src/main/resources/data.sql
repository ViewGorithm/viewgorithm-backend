-- category_insert문

insert into algorithm_category (algorithm_name, category_name)
values ("Bubble", "Sort");

insert into algorithm_category (algorithm_name, category_name)
values ("Merge", "Sort");

insert into algorithm_category (algorithm_name, category_name)
values ("Quick", "Sort");

-- 로그인 Test (유저 아이디 insert문)
insert into user (birth,created_at ,email,name,password,sex,user_id, user_type)
values("1999-08-06",now(),"kuntek1953@naver.com", "이건택", "1111", "M", "kuntek1953", "user");





-- algorithm_code insert문


INSERT INTO algorithm_codes (algorithm_name, c_code, c_sharp_code, java_code, python_code)
VALUES ('Bubble',
'
void swap(int* arr, int i, int j)
{
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
 
// A function to implement bubble sort
void bubbleSort(int arr[], int n)
{
    int i, j;
    for (i = 0; i < n - 1; i++)
        for (j = 0; j < n - i - 1; j++)
            if (arr[j] > arr[j + 1])
                swap(arr, j, j + 1);
}
 
void printArray(int arr[], int size)
{
    int i;
    for (i = 0; i < size; i++)
        printf("%d ", arr[i]);
    printf("\n");
}
int main()
{
    int arr[] = { 5, 1, 4, 2, 8 };
    int N = sizeof(arr) / sizeof(arr[0]);
    bubbleSort(arr, N);
    printf("Sorted array: ");
    printArray(arr, N);
    return 0;
}
',
'
using System;
namespace SortingExample
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] number = { 89, 76, 45, 92, 67, 12, 99 };
            bool flag = true;
            int temp;
            int numLength = number.Length;

            for (int i = 1; (i <= (numLength - 1)) && flag; i++)
            {
                flag = false;
                for (int j = 0; j < (numLength - 1); j++)
                {
                    if (number[j + 1] > number[j])
                    {
                        temp = number[j];
                        number[j] = number[j + 1];
                        number[j + 1] = temp;
                        flag = true;
                    }
                }
            }

            //Sorted array
            foreach (int num in number)
            {
                Console.Write("\t {0}",num);
            }
            Console.Read();
        }
    }
}
',
'
public class Bubble {
    public static void sort(int[] arr) {
        int end = arr.length - 1;
        while (end > 0) {
            int last_swap = 0;
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    last_swap = i;
                }
            }
            end = last_swap;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
',
'
def bubble_sort(arr):
    end = len(arr) - 1
    while end > 0:
        last_swap = 0
        for i in range(end):
            if arr[i] > arr[i + 1]:
                arr[i], arr[i + 1] = arr[i + 1], arr[i]
                last_swap = i
        end = last_swap
');



INSERT INTO algorithm_codes (algorithm_name, c_code, c_sharp_code, java_code, python_code)
VALUES ('Merge',
'
# include <stdio.h>
# define MAX_SIZE 8
int sorted[MAX_SIZE]

  while(i<=mid && j<=right){
    if(list[i]<=list[j])
      sorted[k++] = list[i++];
    else
      sorted[k++] = list[j++];
  }
  if(i>mid){
    for(l=j; l<=right; l++)
      sorted[k++] = list[l];
  }
  else{
    for(l=i; l<=mid; l++)
      sorted[k++] = list[l];
  }

  for(l=left; l<=right; l++){
    list[l] = sorted[l];
  }
}

void merge_sort(int list[], int left, int right){
  int mid;

  if(left<right){
    mid = (left+right)/2 
    merge_sort(list, left, mid); 
    merge_sort(list, mid+1, right);
    merge(list, left, mid, right); 
  }
}

void main(){
  int i;
  int n = MAX_SIZE;
  int list[n] = {21, 10, 12, 20, 25, 13, 15, 22};

  merge_sort(list, 0, n-1);

  for(i=0; i<n; i++){
    printf("%d\n", list[i]);
  }
}
',
'
public static void Merge(int[] array, int beginIndex, int midIndex, int endIndex)
{
    int[] lowHalf = new int[midIndex + 1];
    int[] highHalf = new int[endIndex - midIndex];
 
    int k = beginIndex;
    int i = 0;
    int j = 0;
 
    for (i = 0; k <= midIndex; i++, k++)
    {
        lowHalf[i] = array[k];
    }
    for (j = 0; k <= endIndex; j++, k++)
    {
        highHalf[j] = array[k];
    }
 
    k = beginIndex;
    i = 0;
    j = 0;
 
    while (i < lowHalf.Length && j < highHalf.Length)
    {
        if (lowHalf[i] < highHalf[j])
        {
            array[k] = lowHalf[i];
            i++;
        }
        else
        {
            array[k] = highHalf[j];
            j++;
        }
 
        k++;
    }
    while (i < lowHalf.Length)
    {
        array[k] = lowHalf[i];
        i++; k++;
    }
    while (j < highHalf.Length)
    {
        array[k] = highHalf[j];
        j++; k++;
    }
}
',
'
public class MergeSorter {

    public static void mergeSort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    private static void sort(int[] arr, int low, int high) {
        if (high - low < 2) {
            return;
        }

        int mid = (low + high) / 2;
        sort(arr, 0, mid);
        sort(arr, mid, high);
        merge(arr, low, mid, high);
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low];
        int t = 0, l = low, h = mid;

        while (l < mid && h < high) {
            if (arr[l] < arr[h]) {
                temp[t++] = arr[l++];
            } else {
                temp[t++] = arr[h++];
            }
        }

        while (l < mid) {
            temp[t++] = arr[l++];
        }

        while (h < high) {
            temp[t++] = arr[h++];
        }

        for (int i = low; i < high; i++) {
            arr[i] = temp[i - low];
        }
    }
}
'
,
'
def merge_sort(arr):
    def sort(low, high):
        if high - low < 2:
            return
        mid = (low + high) // 2
        sort(low, mid)
        sort(mid, high)
        merge(low, mid, high)

    def merge(low, mid, high):
        temp = []
        l, h = low, mid

        while l < mid and h < high:
            if arr[l] < arr[h]:
                temp.append(arr[l])
                l += 1
            else:
                temp.append(arr[h])
                h += 1

        while l < mid:
            temp.append(arr[l])
            l += 1
        while h < high:
            temp.append(arr[h])
            h += 1

        for i in range(low, high):
            arr[i] = temp[i - low]

    return sort(0, len(arr))
'
);


INSERT INTO algorithm_codes (algorithm_name, c_code, c_sharp_code, java_code, python_code)
VALUES ('Quick',
'
# include <stdio.h>
# define LEN 7
void quickSort(int arr[], int L, int R) {
      int left = L, right = R;
      int pivot = arr[(L + R) / 2];    
      int temp;
      do
      {
        while (arr[left] < pivot)  
            left++;
        while (arr[right] > pivot) 
            right--;
        if (left<= right)   
        {
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
      } while (left<= right);    
 
    /* recursion */
    if (L < right)
        quickSort(arr, L, right);    
 
    if (left < R)
        quickSort(arr, left, R);    
}
 
int main(){
  int i;
  int arr[LEN] = {5,1,6,3,4,2,7};
  printf("정렬 전 : ");
  for(i=0; i<LEN; i++){
    printf("%d ", arr[i]);
  }
  printf("\n");
    
  quickSort(arr, 0, LEN-1);
  
  printf("정렬 후 : ");
  for(i=0; i<LEN; i++){
    printf("%d ", arr[i]);
  }
  
  return 0; 
}
',
'
using System;
 
namespace Quick_Sort {
    class Program {
        static void Main(string[] args) {
            int[] array = { 0, 3, 5, -6, 12, 2, 7, -3 };
 
            QuickSort(array, 0, array.Length - 1);
 
            for (int i = 0; i < array.Length; i++)
                Console.Write(array[i] + " ");  
        }
 
        static void QuickSort(int[] array, int p, int r) {
            if (p < r) {
                int q = Partition(array, p, r);
                QuickSort(array, p, q - 1);
                QuickSort(array, q + 1, r);
            }
        }
 
        static int Partition(int[] array, int p, int r) {
            int q = p;
            for (int j = p; j < r; j++) {
                if (array[j] <= array[r]) {
                    Swap(array, q, j);
                    q++;
                }
            }
            Swap(array, q, r);
            return q;
        }
 
        static void Swap(int[] array, int beforeIndex, int foreIndex) {
            var tmp = array[beforeIndex];
            array[beforeIndex] = array[foreIndex];
            array[foreIndex] = tmp;
        }
    }
}
',
'
public class QuickSort {

  public static void main(String[] args) {
    int[] arr = { 3, 1, 5, 6, 20, 10, 7, 11, 15, 9 };
    quickSort(arr);
  }

  public static void quickSort(int[] arr) {
    quickSort(arr, 0, arr.length - 1);
  }

  private static void quickSort(int[] arr, int start, int end) {
    if (start >= end)
      return;
    int pivot = start;
    int lo = start + 1;
    int hi = end;

    while (lo <= hi) {
      while (lo <= end && arr[lo] <= arr[pivot]) // 피벗보다 큰 값을 만날 때까지
        lo++;
      while (hi > start && arr[hi] >= arr[pivot]) // 피벗보다 작은 값을 만날 때까지
        hi--;
      if (lo > hi)				 // 엇갈리면 피벗과 교체
        swap(arr, hi, pivot);
      else
        swap(arr, lo, hi);			 // 엇갈리지 않으면 lo, hi 값 교체 
      }

    quickSort(arr, start, hi - 1);
    quickSort(arr, hi + 1, end);

  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
',
'
def quick_sort(arr):
    if len(arr) <= 1:
        return arr
    pivot = arr[len(arr) // 2]
    lesser_arr, equal_arr, greater_arr = [], [], []
    for num in arr:
        if num < pivot:
            lesser_arr.append(num)
        elif num > pivot:
            greater_arr.append(num)
        else:
            equal_arr.append(num)
    return quick_sort(lesser_arr) + equal_arr + quick_sort(greater_arr)
');