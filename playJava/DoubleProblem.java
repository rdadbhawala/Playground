public class DoubleProblem
{
    public static void main(String[] args)
    {
        long val1 = 123;
        double val2 = 123.124;
        double val3 = 123.125;
        double val4 = 123.126;
        System.out.println(val2);
        System.out.println(val3);
        System.out.println(val4);
        System.out.println(val2-val1);
        System.out.println(val3-val1);
        System.out.println(val4-val1);

        System.out.println();

        double val5 = 123;
        double val6 = 1 / val5;
        double val7 = 1 / val6;
        System.out.println(val5);
        System.out.println(val6);
        System.out.println(val7);
    }
}