public class Matrix {
    double[] data;
    int rows;
    int cols;

    //...
    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }

    Matrix(double[][] d){
        int maks=0;
        for(int i=0; i<d.length; i++)
        {
            if(d[i].length>maks)
                maks=d[i].length;
        }

        this.rows = d.length;
        this.cols = maks;
        data = new double[rows * cols];


        int counter=0;
        for(int i=0; i<d.length; i++)
        {
            for(int j=0; j<d[i].length; j++)
            {
                data[counter]=d[i][j];
                counter++;
            }
            while (counter < cols*(i+1))
            {
                data[counter]=0;
                counter++;
            }
        }
    }

    double[][] asArray()
    {
        double[][] matrix;
        matrix=new double[rows][cols];

        for(int i=0; i<rows;i++)
        {
            for(int j=0; j<cols; j++)
            {
                matrix[i][j]=data[j+i*cols];
            }
        }

        return matrix;
    }

    double get(int r, int c)
    {
        return data[rows*r+c];
    }
    void set(int r, int c, double value)
    {
        data[rows*r+c]=value;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j=0; j<cols; j++)
            {
                buf.append(data[rows*i+j]);
                buf.append(", ");
            }
            buf.append("], ");
        }
        buf.append("]");
        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        else
        {
            rows=newRows;
            cols=newCols;
        }
    }

    int[] shape()
    {
        int[] tmp={rows, cols};
        return  tmp;
    }

    Matrix add(Matrix m)
    {
        if(this.rows!=m.rows || this.cols!=m.cols)
            throw new RuntimeException(String.format("Wrong matrix size"));

        Matrix result =new Matrix(this.rows, this.cols);

        for(int i=0; i<rows*cols; i++)
        {
            result.data[i]=this.data[i]+m.data[i];
        }

        return result;
    }


    Matrix sub(Matrix m)
    {
        if(this.rows!=m.rows || this.cols!=m.cols)
            throw new RuntimeException(String.format("Wrong matrix size"));

        Matrix result =new Matrix(this.rows, this.cols);

        for(int i=0; i<rows*cols; i++)
        {
            result.data[i]=this.data[i]-m.data[i];
        }

        return result;
    }

    Matrix mul(Matrix m)
    {
        if(this.rows!=m.rows || this.cols!=m.cols)
            throw new RuntimeException(String.format("Wrong matrix size"));

        Matrix result =new Matrix(this.rows, this.cols);

        for(int i=0; i<rows*cols; i++)
        {
            result.data[i]=this.data[i]*m.data[i];
        }

        return result;
    }

    Matrix div(Matrix m)
    {
        if(this.rows!=m.rows || this.cols!=m.cols)
            throw new RuntimeException(String.format("Wrong matrix size"));

        Matrix result =new Matrix(this.rows, this.cols);

        for(int i=0; i<rows*cols; i++)
        {
            if(m.data[i]==0)
                throw new RuntimeException(String.format("Can't divide by 0"));
            result.data[i]=this.data[i]/m.data[i];
        }

        return result;
    }

    Matrix add(double d)
    {
        Matrix result=new Matrix(this.rows, this.cols);
        result.data=this.data;

        for(int i=0; i<rows*cols;i++)
            result.data[i]+=d;

        return result;
        

    }

    Matrix sub(double d)
    {
        Matrix result=new Matrix(this.rows, this.cols);
        result.data=this.data;

        for(int i=0; i<rows*cols;i++)
            result.data[i]-=d;

        return result;

    }

    Matrix mul(double d)
    {
        Matrix result=new Matrix(this.rows, this.cols);
        result.data=this.data;

        for(int i=0; i<rows*cols;i++)
            result.data[i]*=d;

        return result;

    }

    Matrix div(double d)
    {
        Matrix result=new Matrix(this.rows, this.cols);
        result.data=this.data;

        if(d==0)
            throw new RuntimeException(String.format("Can't divide by 0"));

        for(int i=0; i<rows*cols;i++)
            result.data[i]+=d;

        return result;

    }

    Matrix dot(Matrix m)
    {
        if(this.rows!=m.cols)
            throw new RuntimeException(String.format("Can't divide. Wrong size"));

        Matrix result=new Matrix(m.rows, this.cols);

        for(int i=0; i<this.rows;i++)
        {
            for(int j=0; j<m.cols;j++)
            {
                for(int k=0; k<m.cols;k++)
                {
                    result.data[i*rows+j]+=this.data[rows*k +j]*m.data[rows*i + k];
                }
            }
        }
        return  result;

    }

    double frobenius()
    {
        double norm=0;
        for(int i=0; i<rows*cols;i++)
            norm+=Math.pow(data[i],2);
            norm=Math.sqrt(norm);
        return norm;
    }

    Matrix mean(int axis)
    {

        double[][] array=this.asArray();
        if(axis==0)
        {
            double[][] tmp=new double[1][cols];

            for(int i=0; i<cols;i++)
            {
                double sum=0;
                for(int j=0; j<rows;j++)
                {
                    sum+=array[j][i];
                }
                tmp[0][i]=sum/rows;
            }

            Matrix m=new Matrix(tmp);
            return m;

        }
        return this;
    }


    //main
    public static void main(String[] args) {
        /*Matrix m = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
        for(int i=0;i<m.rows*m.cols; i++)
        {
            System.out.println(m.data[i]+" ");
        }

        double[][] testArray;
        testArray=m.asArray();

        for(int i=0; i<testArray.length;i++)
        {
            for(int j=0; j<testArray[i].length; j++)
            {
                System.out.print(testArray[i][j]+" ");
            }
            System.out.println(" ");
        }
        System.out.println(m.get(2,1));
        m.set(2,1,5);
        System.out.println(m.get(2,1));

        m.reshape(2,8);
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        System.out.println(testedMatrix.toString());

         */
        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix row = m.mean(0);
        System.out.println(row.toString());
    }
}