# 📝 **Aplicativo Busca Endereço**

🎯 **Objetivo**  
O objetivo do aplicativo é buscar as informações de endereço pelo CEP. Para isso será utilizado um serviço externo, ou seja, uma Api que forneça esse serviço pronto para agilizar o desenvolvimento.

📍 **Tópicos abordados:**  
✔️ Comunicação com Api [invertexto](https://api.invertexto.com/) através do protocolo `HTTP`  
✔️ Utilização da biblioteca [retrofit](https://square.github.io/retrofit/) para comunicação com Api  
✔️ Utilização da biblioteca [gson](https://github.com/google/gson) para conversão de JSON em 
objeto  

---

### 🚀 **Criar projeto no Android Studio**
✔️ Crie o projeto no Android Studio usando o modelo `Empty Views Activity`  
✔️ Escolha a linguagem Java para desenvolvimento

---

### 🚀 **Configuração das pastas do Projeto**
Dentro da pasta principal crie mais duas pastas `model` e `service` com seus respectivos arquivos dentro, também foi criado um arquivo ``Constantes.java`` e na pasta ``res/drawable`` o arquivo ``input.xml``, sua estrtutura deve ficar conforme imagem abaixo:

```
📁 app/
├── 📁 manifests/
├── 📁 java/
│   ├── 📁 com.example.buscaendereco/
│   │   ├── 📁 model/
│   │   │   ├── 📄 Logradouro.java
│   │   ├── 📁 service/
│   │   │   ├── 📄 InvertextoApi.java
│   │   ├── 📄 Constantes.java
├── 📁 res/
│   ├── 📁 drawable/
│   │   │   ├── 📄 input.xml
```

---

### 🚀 **Api invertexto**
<div style="text-align: justify;">
A Api invertexto tem como objetivo agilizar o dessenvolvimento de sistemas disponibilizando recursos prontos, dentre os recursos vamos usar a Api de <a href="https://api.invertexto.com/api-consulta-cep">Consulta CEP</a>, como desenvolvedor você deve:
</div>  

✔️ Crie uma conta gratuira na Api  
✔️ Crie um token de acesso para o recurso que permite consultar o endereço pelo CEP  
✔️ Realize testes diretamente no site da Api para entender seu funcionamento  

---

### 🚀 **Instalação de Bibliotecas**
✔️ Para usar as bibliotecas ``Retrofit`` e ``Gson`` você deve abrir o arquivo ``build.gradle.kts (Module : app)``  e adicionar as linhas destacadas abaixo na seção ``dependencies``

```gradle.kts
dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Instalação das bibliotecas necessárias
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.google.code.gson:gson:2.12.1")
}
```

---

### 🚀 **Modelo para Logradouro**
✔️ Dentro do arquivo ``Logradouro.java`` coloque o código abaixo:

```java
public class Logradouro {
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String complement;
    private String ibge;
}

// Criar os getters
```

---

### 🚀 **Interface para comunicar-se com Api**
✔️ Dentro do arquivo ``InvertextoApi.java`` coloque o código abaixo:

```java
public interface InvertextoApi {
    @GET("/v1/cep/{numero}")
    Call<Logradouro> getEndereco(
        @Path("numero") String numero,
        @Query("token") String token
    );
}
```

---

### 🚀 **Arquivo de Constantes**
✔️ Dentro do arquivo ``Constantes.java`` coloque o código abaixo:

```java
public class Constantes {
    public static String URL = "https://api.invertexto.com/";
    // Coloque seu token da Api invertexto
    public static String TOKEN = "";
}
```

---

### 🚀 **Layout do arquivo MainActivity**  

<table>
  <tr>
    <td>
      <img src="../cep.png" alt="Texto alternativo" width="110px">
    </td>
    <td>
      <p><strong>📍 Siga os passos:</strong></p>
      <ul>
        <li>✔️ Pesquise na internet uma imagem que corresponda ao objetivo do App</li>
        <li>✔️ Crie o layout no arquivo <code>activity_main.xml</code> usando os componentes demonstrados na imagem</li>
        <li>✔️ Coloque identificadores <code>id</code> nos elementos necessários</li>
      </ul>
      <p><strong>💡 Dicas:</strong></p>
      <ul>
        <li>✔️ Use âncoras para alinhar os elementos no layout</li>
        <li>✔️ Esperimente alterar o tamanho do botão</li>
        <li>✔️ Deixe um <code>TextView</code> no final par exibir as informações retornadas da Api</li>
      </ul>
    </td>
  </tr>
</table>

---

### 🚀 **Estilo do campo de Entrada de Dados**
✔️ No arquivo ``activity_main.java`` o campo de entrada de dados ``EditText`` está com formato diferente do padrão, isso de deve pois foi criado um estilo personalizado para ele, então coloque o conteúdo no arquivo ``input.xml`` conforme abaixo e aplique no ``EditText`` usando a propriedade ``background``.

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle"
    android:width="20dp"
    android:padding="20dp">

    <stroke android:width="2dp" android:color="#9D9D9D" />
    <solid android:color="@color/white" />
    <corners android:radius="5dp"/>

</shape>
```

---

### 🚀 **Lógica do Arquivo MainActivity**
✔️ Agora vamos aplicar a lógica no arquivo ``MainActivity.java`` para que seja possível buscar o endereço conforme o CEP.  
✔️ Primeiramente você deve referenciar os elementos da tela usando a função ``findViewById``.   
✔️ Para facilitar a organização do código foi criado uma função privada dentro do arquivo ``MainActivity.java`` que faz a consulta na Api, ela recebe como parâmetro uma ``String`` que corresponde ao cep que deverá ser consultado, segue o código da função:

```java
private void consultar(String numeroCep) {

  Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

  InvertextoApi invertextoApi = retrofit.create(InvertextoApi.class);

  Call<Logradouro> call = invertextoApi.getEndereco(numeroCep, Constantes.TOKEN);
  
  call.enqueue(new Callback<Logradouro>() {
      @Override
          public void onResponse(Call<Logradouro> call, Response<Logradouro> response) {
              if (response.isSuccessful()) {
                  Logradouro logradouro = response.body();
                  tvInformacoes.setText(logradouro.toString());
                  
              } else {
                  Toast.makeText(MainActivity.this, "Erro ao converter resposta da Api", Toast.LENGTH_LONG).show();
              }
            }

            @Override
            public void onFailure(Call<Logradouro> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Erro ao realizar consulta na Api", Toast.LENGTH_LONG).show();
            }
        });
    }
```

---

### 💪 **Desafios**

O aplicativo para que não está funcionando corretamente, algumas partes do código não foram colocadas para incentivar você desenvolvedor a criar o hábito de resolver problemas. Para concluir o aplicativo pesquise sobre os itens abaixo:  
🔎 Porque a função ``consultar`` não está sendo executada ?  
🔎 Permissão de internet para aplicativos ``Android``  
🔎 Exibição das informações do modelo ``Logradouro``

---

### 💡 **Melhorando o App**
O tempo de busca das informações na Api demora um pouco e o usuário do aplicativo fica com a sensação de não saber o que está acontecendo, para isso coloque uma ``progressBar`` que será exibida quando o aplicativo estiver processando as informações, isso melhora a experiência do usuário ao usar seu aplicativo.