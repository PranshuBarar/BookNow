# BOOKNOW - An online movie ticketing application 
## This repository contains the backend code for the BOOKNOW - An online movie ticketing application

## Features of this Application
**Secure Endpoints:** <br> *Utilize Spring Security for robust endpoint security, protecting against unauthorized access.*

**JWT Authentication:** <br> *Implement JWT-based authentication management for secure user access.*
_________________________________________________
## Instructions for Running this Project on your Computer
_________________________________________________

## Prerequisites

Make sure you have the following tools installed on your machine:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [JDK 17 or higher](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3.2.0](https://maven.apache.org/install.html)

## How to Run

1. Clone the repository to your local machine:

    ```bash
    git clone https://github.com/PranshuBarar/BOOKNOW.git
    ```

2. Navigate to the project directory:

    ```bash
    cd <respository_name_here>
    ```

3. Run Docker Containers of MySQL and Spring Boot Application:

   ```bash
    docker build -t book-now-application .
    ```
    
   ```bash
    docker compose up
    ```

4. Access the application at [http://localhost:8080/springboot3/swagger-ui/index.html#/](http://localhost:8080/springboot3/swagger-ui/index.html#/)

## Additional Notes

- If you encounter any issues, ensure that ports 8080 and 3306 are available on your machine.

- Customize your application configuration in the `application.yml` files if you require doing so.

- You are most welcome to explore other available endpoints and functionalities as documented in the project.


This is a Spring Boot application with around 26 APIs for performing all the CRUD operations related to ticketing of a movie show in a theater.
# Design of the Application
![img.png](img.png)

# Spring Security Integration (Explanation of how it works)
https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=JWTDesignUpdated.drawio#R7V1pl5u23v80c276wj4SOy9nSdrmNL29yfRpe98xtuwhxeBiPMv99I8kJIwWMAZhOxn3nE5sFhmkn%2F77cmXfrl5%2BzKP146dsjpIrC8xfruy7K8uyA8%2FB%2F5Ajr%2BUR6LlBeWSZx%2FPyGNgd%2BBL%2FD7EL%2BdFtPEcbdqw8VGRZUsRr8eAsS1M0K4RjUZ5nz%2BJliyyZCwfW0RIJj0EOfJlFCVIu%2ByOeF4%2Fl0cDyd8d%2FQvHykf8y9MLyzCriF7OBN4%2FRPHuuHbLfX9m3eZYV5afVyy1KyOyJ8%2FKh4Wz1YDlKiy433Hgv9%2FefCm%2Fzn%2FvYXt1%2FsH8FHyfsNZ6iZMtemD1s8cpnIM%2B26RyRQeCVffP8GBfoyzqakbPPeNHxscdilbDTizhJbrMky%2Bm99txFwdzBxzdFnv2NamcC68H2PHxGfQ32Zk8oL9BL7RB7rR9RtkJF%2FoovYWcnPmBzzFA2CZyp5ZaHnnerZjnsdR9rKxYyPEYMKMtq%2BN1c4g9sOvVT%2BzOa%2FfQ1yP8bL%2B0%2F89kkublZzSaWfcjcgsPnNkLBYqabW28WoIdFNbfKRGqmu3luoeUJc%2Bvxua5NrAdCdWJtzxo%2BszfuL9bTw29r8NdkEkfL7O84ziYQKhOJ5njXsq9ZXjxmyyyNkve7ozfiVO%2Bu%2BSXL1myCv6KieGUkKNoWmTj9eMLy1z%2FZ%2FfTLX%2BTL1OVf717qJ%2B9e2bdGgG%2BybT5DLe%2FJAFRE%2BRIVLdcxVJM5aF3SHCVRET%2BJlM346nh8%2F9Zx7yUFQXCGp6C%2Bbt4%2F24yfmGzozF%2FjC6CzfqEzx8%2FjT0vy7zVelec4xwyBjYifsBy0PK%2FFxS%2FRA%2BZKwlpGSbxM8ecZXhOEd80N2SMxpvrX7MQqns9L2CD8WNEDHY%2Bs5jqL04JOmXtz5d6xZ%2F8QreKELPhPKHlCZCR2gqEJQvad79I5WkTbpNDCoxXzms3MWB97SIG7aHc0mOIZtoRNzUbqDBA2%2BG9kMmqXZIvFBiNVRlD1DP1BpaOlB2LKa8DUH%2BjhC5pt87h4vc3SRbzsiq3ejHG3%2BArpDkMPBoSiLPNoHqMdYCgz9e1wXjt3h3fCrIgzAtiUUDRD7NSGEjv1VF5qA69isSLVH4msqJLKxz%2FuMT14xC%2BK9xuZhveE7jJMlkv4kO9WrzwSywcYfmbVPO%2FwYS8WABB2J0MmXq0TtEKEEDRhpTpc%2B0Ew5DebXxXPJ7iezdBmc4fSGM1%2FilJMu%2FLyxBeUx5jalSTsjQPbtUVRpklMhLaKa9%2BZumOJM%2F5pxZmdBPOXIMCYFmeCjuIMdE3LM%2FTW6zyPXmsXME6ucjOOJa6pMrQ4PqwvtXq933o9%2FlA%2BgVG%2B6DoXYesAYSvPiojRlVAP5tYdOlz4AlNg245IhaxhwhcbWRo0lJSvPqLZ7xuU%2F%2FvhKzGrWCApF3anxrbLa3btSRmQv3d1rTN9s0zTtwb2g7%2FWlvAQqmJA3H5DVKUjFbH5rJpQ4TzPE3f8QBWOjcyNVq%2Fa%2B8fT7wKddP8Z%2FbNFm%2BJDnJAVbhPpNXhUkddF%2Bvap%2FK3ei14KlM43NRm%2Bk8S%2F%2FwcBcMBiof7gv9MZ%2Bg3l2il4q%2FL7xJ76roBPCzqK%2BA59q7qsLsF7Bgy9rfRcBC%2BhvHeoiOJkg3Wwp3iGfsZa40AlUNFi65oo0GFQfYy3jiI3kGicC6aqEggDja%2FAs8dTAl0diO7x9KW%2FF3FyHOoHHD3162nvGILzi%2BGiBlkIRHOt61gqYF2oASwYCa6VU%2FM7l%2Blh2FWoN260GOaECS8SvHkJnqPeiATvAi5sD5TZK%2FPE%2BGJ6tW%2BOuu2PsX2N6%2BS9bI4T6EjCCQBSVId0RxBYA29g30c1U3oXg8IY5MikT9gJAlG3mjhGqBO0RURPjkesnNPKKMfyq3QmcpZxIjdM5VFlFKKtUkcn93Pe4v2RZ0mit%2F%2B8EfF%2FYgPRcelwR2Zd%2FuciX13%2Bd5yR1o5DqZmgD3Buf4rSaDmSucvEcvhANL26jsZ%2B4GicyPZYJihuC77w10PdgK2k1wBjdWxnimmI4wMXS162C3wROjDAp%2BkZK3QgbAGInr2GInPlgtb43NUHGshJSNizF6PNugzZXsQvZP8esKwHh8KKfL2KxSZflkm02RwMhZYlt8Q1dgI1YtbyNdaa6qD54Klmc8BaSxySOEUT%2FmyEPBBLktvFsLiX0AQNdCbJlnFKBIB3H58L5vf4QeX69LLr%2BSpOddc12yXXhsSGrhBtjnhvipE3gz1bwp4qKfDQAAF6Y9m1gz7BTXh7rslZRmzJTJ1OMKeBDzfR7O8lfYR9MuPueHeGN5CdoXR%2BTVJdyG8RWhbP7h%2FjtDzxIU74BGywgsBHs6rn5lktYEqOLZLs%2BTqNV5w9DjO%2FcFPDXs2Eo%2FRMNBP%2B3OdMLgmlfL%2BKiGsIROkc%2F%2F0NL%2F1zls%2Fpbqcv%2BYDInL4UeTQrEDm%2ByLMV%2FmfnWz8%2FuqmHuLjH0ixFplQrV5SgXI1H2dcQTGc07LlnRzAnYApAINDMqYdFina6Sb%2F9hvIYzwshf98sMdWQSsc4qfS7GnE4bToTUglDXbrP8c1u468Q51H7fQkh6LhEO%2BsrAFz1G9nBYEsCosdMS03ugokjGTukG0R%2FwTFTgvh6jMmkN%2BsoHcKko51dC73bYo6NSo59SzFYsmusu1SRO2aCJ9wbxuf1ZjX3rkcUBz5WTsabExB2mR9cRPBVfR4CnbnPGonohvbZyQhvQKk6DpfpKgdwvnsmckDwDViYmCaEr7jdbopsVYuNxMe2mzhd4n%2BTLJqTMzev5G8arUrKTXWtEWn1HprcHrbnIvSgDv7xuWiKQu39W80v0pOrvFG1UwpC9fgYdZ5i6Tx6o%2FGUPhF9PXgKOISnOJ4vqp3AqvTQN6p27iaUvduJtBxO8M%2BE%2F4RnYzb5TrBmG4cWl1j2izbOeUGruaBJR9GmkmyAJKn4DZLKoRJP0zhLlKIc6540j%2BDdTJZ6DnObHfTKEPZ%2B6Z4ZDT0m6QSymuWEoZjv1X91sXynJIi0P9IR5qinGPi9y3uciHB5jzthBXnPUeU9azR5rzlkqOMWJzeYUdiaSvN8QdSxVRCQ438fMFOlX6m2RgLEwLtZQgLviF4Wp5QjbNZZumnOGHorgLNcSwScrwJO59eScwnMOQpAn9yBi9HqWzBahX5Xye7MlAZd%2BcszM1ptMEK2axoXRf58Rsu7%2B39r4qLK68rAKPpXuvJ8KOFpIqMsT4qh5hYYwYqvSfq17JHQB3vFRn0DntKwqw0bBueVNRkewYg91KW4iZ6QSg0AOazb%2FHVztbE88XGt2cSc7XW3Zg%2F6KeiSmsBKKOo5GcjfptuV1FqQin24tsZGDqypptbHaNFZVSZ%2BW7jLbJs%2FVfOvFclqCyDS8wNFTIphircjCpk6mbyDJK%2BLNhX5%2FSKYodmsDUB7%2BUQNGi5QccGPDc24VhKy5FrXJY9kt7WF19hQFkukkg0lE1VGMhVFA4FqB7h%2FjIlDlP6hzComiFoQEsJjTYtH8rmqC1LKDRbIFuQoLUaz2KY0FW5zJYajrvPsKZ7TaNQHsmJf1nnpd%2BVFeMlvkBnKt2kRr5Cyu84oqcQEofO4IYZTOZ1cammiS%2BB4ginQhVad7zIcL7fHBWC6vwKVzW0vQjCQa421XJoCVFxQmMdPWrkPv28xYRyDSJtft5siXry2SoT44ehwxxU7RDQgiJVCX4eG0PPtyFBDCdeSFtkK1EUGOmutPZr5DKolFCpVYCuoAiWF3ikEkcYaMFJhbKIAZDnlD5R7fM5I8SaB%2Fq%2BidBslCZltGhhamnN%2F%2F%2FL%2BM%2BEf5N7ru08%2F%2Fyq8R42dXFVJDCULIq8u1ler%2FxiWfhI05z%2FChtrdeMWsxp%2FROjtUPj%2Bh0G0E4YFEyBwegS5gXCtdy0mmBkF%2BqEGujY6BFu1c9klCq5snVq9GRo59qE%2BvSb1XfLREmSbj7IlL8w%2BLqXpTc9pM9C5zdZmry1xd5uoyV5e5uszVZa4uc9VrrvwOqtN5%2BCtMqE62WN7FslXFyeJWXME4AEcrHg3V%2BvntGFRh5o8dzPW%2Byr6hpQvSorT1Mm2%2BNGGsMwrzmgVhuytz8HYQ5osmYUsTu6VNOBzPIqyp1WceF%2F8i5qJ%2FXdUsPDsIMLh0q0D2ja13FQz6ys0uqqMz1Fgb4Vi99mBV6Lg3PXGMBUY14eY2R1FReqTwH7Qq1tSdRGyKXrQii0x%2F5sc8Sgs0J6nQWeleYme%2FqUQ0E1QlEEOQbUulKr6OqIzmS6%2FeYUSUdQ232YuyL9TZqaIJZKxDFsnpLy3apQOU2b5LK%2FZ6W564Yhbn0gYdPWTUIk1v%2BzYCPsYgd47q8Qw05I73TRsBiM0OtPMDYoT%2FT9FzRcfSh826FOB1Wc0cnXhy8dxqeSqohir%2F1oC5iuYVPhmSc1Rs85SB%2Bc1gViGeGi89d6AIxDMcDbNnWQPC8q7EfF27uuKN5uvqQmvT7ON2xZOhG9G5v%2FA37NxykMvvZxJoC3vVZh8dvUBMB5mSnJULekdDb%2FfmHF0Lah0Lvd45oteVaS%2F0LugdDb0ckx3Qe16VeqB1liV5gYBeUhLPdS7wHQ%2B%2BXYsxVMrRucCXx1GOFIF%2FUCrUmcSjw1DqKiBXnusajg59ybfhSTp3QzT6oWUobR6yytUplnk1ahMqqGlZoGfkR%2BhewcmEPmJ1D%2FINtKG3LEcsfMwLSw1NjZA6UlTByeMXBIXcbTIgDjpBi6LVjKP1B7eFRR8BT6fAjx06YuJKv%2Fq1Cn4k%2BgOOBh5tz3uDbCUTWkexAOpvk91IneetUPJrd%2BY2lmhUcy3JoWWI23DSVlE65wjMRtPyUF8A41yZDX9%2BE80FYQgktmCG24j4mdiStXVMXqNKoWW1Gg2bkO30mkP3ZYmbHjfTaAn5qn4P0fuQEqRxtF%2FufYjVgqUZJWfMsU1uQteT%2BlKZ2YLiHgy9o21BVyfuvXFFUEpcc6EUEdSVNUvlyhQWb4gzQ1cUIu2gpbmAOdasAkdFzpFZc3gIVeDPb0IP9KQEdJ6CNRCJIg6P15YQ%2Bjo5%2FiBffmAsdClsL0SONOJCCytdZoV6sHTH70bqE8h0TgzPILQ9WyRi3gjAhvBowHY7JOW%2FMXbHh6msBZK5oDO3C0Su6Yyjh04sidQ6oPWxdtVmOHt0j8Ieh8f6wqYoJrdrCO850SS%2B8UwI4ZgpiNYT28hOgCKCJ%2B4RNWE1ivNXTHR2NcAOVQbxQPGcxrrJ99OoSd0QppROVmm2w6Wn1LijZRSnm%2BK0D2Hy0LYesvhGCAGwJfHEGih4H0ME0ZVneNsiiCfl6kCnr8YtUnAYSq3gjXleRSPQxHWPIVKosquu0AXVcISGtSpNe8eSg%2B5uSK0b3rSppjadMf3g28cI%2FQilzrWBIcVdlFGto8kRnprDqA8SPVeHibHVJaZaR6pANdC7egzmoMtBfdvMAcqpwqCnp9Tjc8m5g%2BzpMsUdPIkLsSzTcbmDqyqcm7LzQ42slyW8JEX0oOJkH%2F%2B4b2yHpxWkG2xpO15UZipzVxJlRF0eEED8jKEmxUdlid%2BessypgBEe50glgCe2GXVZ2k7Hs057qgStz%2BM5VyZncnndIBDXYQIH0t1jmDuaS9QZCzLrVXqxR8ESIW6t%2FR3UH7x%2FRBtaTvGZEOZZtEFyqd%2FHKJ2X5RcfUY7af3ebyEcSfcX6o0zm72m9c%2Fe8cSZpKQsyB3lM357yqGg2QxvybUMqGtP3xxuyjMjeDZQoBTdP%2F8JZjknCkd4WH6yvuUQCD0sPPUXhZl%2BS0Cdh1Sd8XyEY2ZlusKOI20icutaBMZbI3FQt6HPVoksgFhtEUVvv8VX29mqRzs4663jE1ghwakseK54AUK8Y4lcNFISMZDAW%2BHgH4f7gg3ZjsSvXUKfMpnHixZXO2lRjAzRUTNEAsJhOaZ5DaraAGrbVaxna1RM1%2FB%2BpGslo2HS4OMt97lzSFsqjq6h0TKDSe7m%2F%2F1R4m%2F%2Fcx%2Fbq%2FoP9K%2Fg4GdcoIWZtkbrkOjMFeomLP2ufy%2FRMl33bJbeRL0Ju25%2F1L7W7WnLiGhdRMol0yPyy9Ut9pBYbgayjSZbIzh02oCX7RsexrE9sXzSdTlidE2PGEx2%2BVdPJZ%2FTPFm1oCZoczVD8tOu1warOrNdJRdCa6RSlSrTzxwyti90gf6AH3qrjNksX8bIsfaMZp5T6ya%2B%2B7pj9OqLSYvGId9LyUXvjU5TH2baSKVmduDjBj7JpIKCLjP5UjqLZY2mIKd8Vb%2Bc1W6ZSG8k301Po2xr9uo1aGYk08Hlrisp4YiYCyhFJ%2FMSReh6YUb510wNVCYOh%2FaoqGseFRmmNiRbVgQfWVQl2qDsMdExX5BcmWKxsNOGTUqPLnoYu22NxWG3tSWn2SS72uvvLHw53xxYnhX%2Btix183sQmOqPlKfMSVm3zMkjN5d0xm%2FtpdqQ6sInstDL%2FEedT%2B5CqantPSXzZ%2BPSK16dKsiWtsFaj%2FPmOI9Z52zzG%2FLHYtQcpOQaxcVwLUj%2FmcniGk4TYPgin43yNdRARVYSoehDetXqJUpRXDbJmmKGpHOggHJhfVXZWbgkCVaWyMn%2FWFz0Ixlpz1dZ6Te%2FK8JTnXD6o83lNtzFinioXuRI5tGLFjAozzIRFIaSRdECzqEFaj2M8xU%2FS85zlUsPQmfKedadYbX3bMa1kNnI73EautLeGBhOD9upRbL7M1c%2FoqZ5IEQVOIK3YCK5dDrEhocROg8UGE%2BnsGZPwc%2FKSDjQZ5lnB7QahXlxs3ThG1AbHldRlNtJQpUGkNTyw%2FQg6gy6a3ZwFSGfvOVxQE%2B1I%2Blyw8wpt2WVAc5zIXvTOBpqqgH6DpcdY9oWU6TYxTAB16NMW0jaHvloBLccKREshwAvSo37WUPC6DZuiG59tk%2Fz3l6rqWuTyWF2CJS8NlK3c3bsEB3J0sDSSsT0iVXXhVYLG3SS6OuAGSfRIiK5XrxO2Xl8TvTFJ8lgswIVTO4TQAo4TQD%2BUbDDQDqag9l9fBiHFgMGRctEnUmIWFGuSqde70utabuv1UrS213qxJ25Dr%2F1RAunJWcEbU5v2ZzT76WuQ%2Fzde2n%2Fms0lyc7OaTXR79rAwKKHvkSz1wz3%2B3sYohIeq9OOERcBcU30%2BLuIoaR2ys0bS9GzUh%2FyO2n3%2Bj%2BSwdevK2RDEcEBs1nc%2FsWoIiC6rrKxSf73HY99trFqXYpZiYibXrmZPoh6JnsOUFsZVVutn3HeovHKgUCNoSp%2BKmcUu4DUBXtod7ZAA88u8dnk2lJRxNhcae0QaK5CcinSYoY06%2BkP9LXHxmG1700rMFlhuRuklMsIfeN7hRk%2FPL0gcTDBPOo9N7yb%2FwhDfTksMnsaQq6iLLa4dUV%2Byua217thhxn%2FRd%2Bsb8OxodYXmkkxDKynxWhG0fuI75nK9VXKLf2gE1JAV7OuQH7S8Eynh3LPU%2BI8qzUgIADEQda5f3ZP47ZptjEqkY4fJHZroKeVie640RPcC7HDq1GwmUnFFucBRg%2BnDlI6vi2Axs2%2FxU%2F%2FOczs%2F5Nmqtn%2B%2Fo70KYd%2B96oy0V5vzQ4avKA1zYZT4e6bANnB7rupYFHjcynAHdlQhCc6AVTc%2FWk8VXXuaDk1tpKYrlgqpRTBDs9kg3nKs0q%2BiOdoNpLDczixI6s3hyUULRmY6Hfz1PWKohslcVbenir9rUv24P0DsyG2NtOc1sdCmSHm96kJ5%2F%2BiUu9pmCi15CFzHBYbW0Zayk3yVcvNStcIqjkW4YbMXhS9iSfp2iwRb1uvn9DOaZfl8c3dTLd0e04zCqKdcNKPdYad1QU1rLPleoAEBnHoiPHweV1nf5dCeWpoENrkgizmIDFeeIehrBVJvKUs7zrIVmdorsUIL9UoclKw7%2BA0aoN%2FyJHvBy0rPFPmWJkARnx76Zxs1VonZDZjl6jE22iIqrdV0uLQsc8YG%2FZ63FFC2FHdVd9hScoyLuS2l04JOLGJYoWxBUCUMqOswHY6lKkKdVnEwb2LFkSRKwXO1NDUAS9cmqwYQV37JefajoGCS4n%2BLbco6ePDu5pSModWahvavImLvz8iOw1pOvOocKHz%2BW8uGkjzqadDiBlWZgDpgfHskwHQJ4zr1tvL5259uWw2vH9TFU9G1SsfLZLOKktKjAw53%2Bsjbkm%2B028324StWxKUQmO5emwNIzBjTVy9Xs88deP8Yb2RqRHHATWLdXIp9D%2FVwYPLUtJLWli0kGLWt1kRXsGdk75pJZ6TgQ%2BwOu%2B%2BANQAxVNgPoErxPEvPGsZyAHbJETYTBn9QJC4vzLErxvEX%2F%2F3GwhxD%2FE5isHur1FWPBW7hI6eyNtqSQcyXW2Z0tTZavjSQ3EDMVGVT3qaA%2Fw5rAjVqjOzI2R9jQ9HvCkXvlFB0gOhk80M47VlmVxnK9uShjLUkFY3svgemZgux6wHpKvg7gqu%2BRp6BbQsEehr64T4irc1SGhn6oCP0HT3yO0N6mMKl715%2BxoEXHabe6Up0GjJZx82i5gWxd43t%2FFaKolzvtmdySLWqpMtHIgodPG61Ai6ds4wPipSQstNDoOrpFtC4ZkbT060OvU7MWzNaFqjZzuraUxtAz3dD3%2Fdc29OYWF1Pe4npEnz6x9cJ%2F4dWGz7UY9BUfRQTlVtio5CCjspIY269AJNKY%2Balb8tTxB32pbRsvPuhOaTFfLDo4aokD72oygWPhT7HmZL96gKLZACGnihnazxodjB1A9%2F2HYpIT0XiaO5W3nHpZOLPTtjh0k91wRlJP3zRRpd%2BerFgy5E0uUDoUaG53mu7fhyeauu8tuei%2Be1dV%2FukCh2U6mhWLYkObqkVikqW50nPYsq2ICfNh0Hrc9lSd207bE8GdiRji7NnfMcRr3f99sxkT7re84%2BgitqqE9bXUufjdcKgBTMaGWUXIbeFFjQzUDB15F5ejpGNVFXm5AVPPEnEM1J1p4WCDIpasZpiPq4XNDWMVpLlBdh4ZbfSo4w%2FLOoZZNFVPc%2BV3IfEGrSLbc4qyvGQUl3B2col8o6GcUTpa10ErDlM6G%2BWUSSP1NFCi8Wx3%2BSZc3FZaW5Xp3AED0SUzxi2bRLbWuQIfcqeyKTVS1EM8jeEUj6Ky2t%2B1UtF8jwGId0oOFx1w1%2FzjIBhh1X8zo%2BfsjkhEe%2F%2FHw%3D%3D

# Database Schema (EER Diagram)
![image](https://user-images.githubusercontent.com/117909106/225558558-376e21dd-8f01-4af5-8f58-b8d4bba940b8.png)

# Description
This Movie Booking application can do the following tasks : 
* USERS  
  * Users can register themselves on the application.
  * Users can deregister themselves.
  * Users can update their address. 
  * Get details of all the tickets booked by a particular user.  
* MOVIE
  * Add a new movie in the database.
  * Remove a movie from the database.
  * Get show time with the help of theaterId and movieId
  * Get Movie with max shows
  * Get Movie with max collection
  * Get list of all the movies with their total collection
  * Get total Collection of a Movie
* SHOW
  * Add a Show for a particular movie.
  * Cancel a show for a movie.
* THEATER
  * Add a new theater in the database.
  * Remove a theater from the database.
  * Get theaters with unique locations.
* TICKET
  * Users can book a ticket for the movie. (User can book multiple seats on the same ticket)
  * Users can cancel the ticket before the showdate.
  * Get details of a ticket using ticketId
  
# Steps to perform operations 
# Users : 
## => Registration of a User : 
![image](https://user-images.githubusercontent.com/117909106/224682460-66542e35-bb76-4dd1-9862-07c9f57da626.png)

## => De-registration of a User : 
![image](https://user-images.githubusercontent.com/117909106/224682066-29767601-6803-489f-abc2-da868787ea4c.png)

## => Updating address of a user : 
![image](https://user-images.githubusercontent.com/117909106/225541680-2b4daeef-e59d-433a-b996-e00ef054f2fb.png)

## => Getting details of all the tickets booked by a particular user till now : 
![image](https://user-images.githubusercontent.com/117909106/225540733-b24bfc4d-104b-4afc-b71c-cec9d0273a1a.png)
![image](https://user-images.githubusercontent.com/117909106/225540876-fb5e6b15-cede-4f46-b465-f1f92eb73db9.png)

# Movies : 
## => Adding a movie in the database : 
![image](https://user-images.githubusercontent.com/117909106/225545134-9b239479-1f70-4496-a038-dcfcd9a94a05.png)

## => Removing a movie from the database : 
![image](https://user-images.githubusercontent.com/117909106/225545392-cd89e987-518d-400b-b43d-3fb6240c8c4c.png)

## => Get show time with the help of theaterId and movieId:
![image](https://user-images.githubusercontent.com/117909106/225545633-8827f453-7aeb-4774-b7db-6ecaa3c01909.png)

## => Movie with max shows : 
![image](https://user-images.githubusercontent.com/117909106/225545781-1d0f5889-d8a2-4586-873d-a10df4c38c20.png)

## => Movie with max collection : 
![image](https://user-images.githubusercontent.com/117909106/225548380-73c247ea-0750-44f6-8bcf-6e51f215620d.png)

## => List of all the movies with their total collection : 
![image](https://user-images.githubusercontent.com/117909106/225548557-fcd09e68-e740-44a1-8a72-4731717f5a4a.png)

## => Total Collection of a Movie : 
![image](https://user-images.githubusercontent.com/117909106/225548766-c7cc83f9-1748-4425-9a40-6aa4d5e0649d.png)

## Tickets : 
## => Booking a ticket by user : 
![image](https://user-images.githubusercontent.com/117909106/225538866-05cfde4d-c379-4413-b982-da8a08c90b2c.png)

## => Cancellation of a ticket by user :
![image](https://user-images.githubusercontent.com/117909106/225539189-46829e4a-bc98-49b9-9f0c-8122c70b80ef.png)

## => If any other users tries to book the same seats which have been booked by any other user earlier : 
![image](https://user-images.githubusercontent.com/117909106/225540020-8e810a11-7d7d-4023-96ce-5d203406e14b.png)

## => Getting details of a ticket using ticketId
![image](https://user-images.githubusercontent.com/117909106/225542658-81035e26-2925-42a9-8070-51da2271c997.png)

# Show : 
## => Add Show : 
![image](https://user-images.githubusercontent.com/117909106/225536371-ee60ad0d-6296-43be-9449-7a97d373b848.png)

## => Remove Show : 
![image](https://user-images.githubusercontent.com/117909106/225537232-d5d2b08c-5556-4fe2-8e5c-150aafdf04fb.png)

# Theater : 
## => Adding Theater : 
![image](https://user-images.githubusercontent.com/117909106/225534903-a6470f3c-ecf5-4d4f-bbc1-489661a48460.png)

## => Removing Theater :
![image](https://user-images.githubusercontent.com/117909106/225535153-a9aae4a1-d2a4-4a61-9b4a-e17b9cef556b.png)

## => Theaters with unique locations :
![image](https://user-images.githubusercontent.com/117909106/225535437-931bfa30-a86e-449d-b0e1-028726ce9f5d.png)


_________________________________________________
# Tech Stack
_________________________________________________
This project uses the following tech stack:
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security/)
* [Hibernate](https://hibernate.org/)
* [MySQL](https://www.mysql.com/) (For Database)
* [OpenAPI (for API Documentation)](https://www.openapis.org/)
* [Docker for Containerization](https://www.docker.com/)

## Contact
For questions, feedback, or support, please contact the project owner at pranshubarar1851996@gmail.com.

## Conclusion
This ONLINE-MOVIE-TICKETING-APPLCIATION provides a strong backend infrastructre to do CRUD operations and managing the database.
