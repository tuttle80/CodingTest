int EOD_1 = 0;
int EOD_2 = 0;

void getNextPoint(int& doc1Index, int& doc2Index, int beginDoc1Index, int beginDoc2Index, int& endDoc1Index, int& endDoc2Index)
{
    //doc1Index++;
    //doc2Index = 10;

    if (doc1Index == endDoc1Index && doc2Index == endDoc2Index) {
        doc2Index = beginDoc2Index;

        //
        //endDoc1Index++;
        //endDoc2Index++;

        //doc1Index = endDoc1Index;
        //doc2Index = beginDoc1Index;
    }

    else if (doc1Index == endDoc1Index && doc2Index < endDoc2Index) {
        doc2Index++;

        if (doc2Index == endDoc2Index) {
            doc1Index = beginDoc1Index;
        }
    }

    else if (doc1Index < endDoc1Index && doc2Index == endDoc2Index) {
        //doc1Index++;

        endDoc1Index++;
        endDoc2Index++;


        doc1Index = endDoc1Index;
        doc2Index = beginDoc1Index;
        //if (doc1Index == endDoc1Index) {
        //    doc1Index = (endDoc1Index++);
        //    doc2Index = (endDoc2Index++);
        //}
    }



}

int main()
{
    init_apartment();
    Uri uri(L"http://aka.ms/cppwinrt");
    printf("Hello, %ls!\n", uri.AbsoluteUri().c_str());


    int doc1Index = 0;
    int doc2Index = -1;
    int beginDoc1Index = 0;
    int beginDoc2Index = 0;
    int endDoc1Index = 0;
    int endDoc2Index = 0;


    EOD_1 = 5;
    EOD_2 = 5;

    do {
        printf("%d, %d\n", doc1Index, doc2Index);

        getNextPoint(doc1Index, doc2Index, beginDoc1Index, beginDoc2Index, endDoc1Index, endDoc2Index);


    } while (doc1Index <= EOD_1 && doc2Index <= EOD_2);



}
