package com.example.application.data;

public class Texto {

    private String[] Titulos = {
            "Titulo 1",
            "Titulo 2",
            "Titulo 3",
    };
    private String[] Paragrafos = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce consectetur rhoncus massa, non malesuada libero mattis ut. Cras non justo leo. Morbi sagittis tellus nunc, in aliquam elit pellentesque at. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Quisque faucibus eros tempus neque luctus tristique. Maecenas nec tempus eros. Integer non purus non elit mollis iaculis eget a tellus. Nulla id rhoncus lectus. Etiam vitae ornare libero, in sollicitudin metus. Nunc tempus lectus at tortor condimentum elementum. Mauris faucibus sagittis pellentesque. Nulla tortor nunc, semper a efficitur sit amet, bibendum sed felis. Aliquam mollis euismod aliquam. Ut accumsan orci vitae metus condimentum, quis scelerisque justo viverra. Fusce non neque eleifend nisl euismod convallis ut sit amet lorem. Praesent volutpat pellentesque nulla, mattis vehicula mi maximus quis.",
            "In gravida justo non consequat molestie. Vestibulum eget diam quis justo volutpat dapibus a non ligula. Curabitur tincidunt felis vel interdum varius. Aliquam ac purus felis. Nulla commodo tortor sit amet dui placerat efficitur. In efficitur egestas ex, porta semper quam varius a. Mauris nec risus ultricies, dignissim massa id, fermentum enim. Maecenas in libero pretium dui finibus blandit in facilisis nunc. In dui felis, tempus vel elit quis, varius pretium metus. Aliquam et magna ut tortor molestie consequat at a sapien.",
            "Fusce laoreet ante in est varius varius. Proin quis varius neque. Aenean imperdiet malesuada ligula, quis iaculis metus scelerisque cursus. Vivamus mollis, tortor quis mollis blandit, sem nisl feugiat risus, eu rhoncus libero urna iaculis elit. Nunc lacinia semper nibh vitae malesuada. Maecenas sed sollicitudin nibh. Suspendisse dictum justo porttitor orci fringilla sollicitudin. Proin nec sagittis neque, condimentum porttitor nibh. Sed dignissim, nisi non aliquam venenatis, eros lacus fringilla dui, vitae ornare metus odio a risus. Mauris convallis eleifend eleifend. Nulla laoreet justo laoreet erat tempor, at lacinia erat dignissim. Aenean sollicitudin lacus id mi rhoncus, ut sagittis dui congue. Etiam eros risus, dignissim eget ipsum sed, pellentesque iaculis mauris."
    };
    private static int Pagina = 1;
    private static boolean Sucesso = false;
    public void setPagina(int pagina) {
        Pagina = pagina;
    }

    public static boolean isSucesso() {return Sucesso;}

    public static void setSucesso(boolean sucesso) {Sucesso = sucesso;}

    public String getParagrafos(int num){
        return Paragrafos[num];
    }

    public String getTitulos(int num) {return Titulos[num]; }

    public int getPagina() {
        return Pagina;
    }
}