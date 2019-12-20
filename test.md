## Skenario Unit Testing yang dilakukan:

1. RepositoryTest:
   -  Memuat Movie
     - Memastikan data movie di remote repository tidak null.
     - Memastikan data movie di local repository tidak null.
     - Memastikan jumlah data movie di local repository sama jumlah data movie di remote repository.
   -  Memuat Tv
     - Memastikan data tv di remote repository tidak null.
     - Memastikan data tv di local repository tidak null.
     - Memastikan jumlah data tv di local repository sama jumlah data tv di remote repository.
    - Memuat Favorite Movie
     - Mock data favorite movie dari data movie
     - Memastikan data favorite movie tidak null.
     - Memastikan jumlah data favorite movie sama dengan jumlah data movie 
    - Memuat Favorite Tv
     - Mock data favorite tv dari data tv
     - Memastikan data favorite tv tidak null.
     - Memastikan jumlah data favorite tv sama dengan jumlah data tv 

2. MovieViewModelTest:
   - Memuat Movie:
     - Memastikan data movie tidak null.
     - Memastikan data movie sesuai dengan yang diharapkan.
     - Memastikan jumlah data movie sesuai dengan yang diharapkan

3. TvViewModelTest:
   - Memuat Tv:
     - Memastikan data tv tidak null.
     - Memastikan data tv sesuai dengan yang diharapkan.
     - Memastikan jumlah data tv sesuai dengan yang diharapkan

4. FavoriteMovieViewModelTest:
   - Memuat Favorite Movie:
     - Mock data favorite movie dari data movie
     - Memastikan data favorite movie tidak null.
     - Memastikan jumlah data favorite movie sama dengan jumlah data movie 

5. FavoriteTvViewModelTest:
   - Memuat Favorite Tv:
     - Mock data favorite tv dari data tv
     - Memastikan data favorite tv tidak null.
     - Memastikan jumlah data favorite tv sama dengan jumlah data tv 

## Skenario Instrumental Testing yang dilakukan:
   
1. MovieFragmentTest :
  - Memuat halaman MovieFragment.
  - Memastikan RecylerView dalam keadaan tampil.
  - Memastikan jumlah item RecylerView sesuai dengan yang diharapkan.
    
2. DetailMovieTest:
  - Memuat Movie:
    - Membuka halaman DetailMovie.
    - Memastikan TextView untuk title sudah tampil. 
    - Memastikan TextView untuk overview sudah tampil. 
    - Memastikan TextView untuk release date sudah tampil. 
    - Memastikan TextView untuk score sudah tampil. 
    - Memastikan ImageView untuk image sudah tampil. 

3. MovieTest :
  - Berpindah ke DetailMovie:
    - Membuka halaman MainActivity.
    - Memastikan RecyclerView sudah tampil. 
    - Memberi aksi klik di item pertama RecyclerView.
    - Memastikan TextView untuk title sudah tampil. 
    - Memastikan TextView untuk overview sudah tampil. 
    - Memastikan TextView untuk release date sudah tampil. 
    - Memastikan TextView untuk score sudah tampil. 
    - Memastikan ImageView untuk image sudah tampil. 
    
4. TvFragmentTest :
  - Memuat halaman TvFragment.
  - Memastikan RecylerView dalam keadaan tampil.
  - Memastikan jumlah item RecylerView sesuai dengan yang diharapkan.
    
5. DetailTvTest:
  - Memuat Tv:
    - Membuka halaman DetailTv.
    - Memastikan TextView untuk title sudah tampil. 
    - Memastikan TextView untuk overview sudah tampil. 
    - Memastikan TextView untuk first air date sudah tampil. 
    - Memastikan TextView untuk score sudah tampil. 
    - Memastikan ImageView untuk image sudah tampil. 

6. TvTest :
  - Berpindah ke DetailTv:
    - Membuka halaman MainActivity.
    - Memastikan RecyclerView sudah tampil. 
    - Memberi aksi klik di item pertama RecyclerView.
    - Memastikan TextView untuk title sudah tampil. 
    - Memastikan TextView untuk overview sudah tampil. 
    - Memastikan TextView untuk first air date sudah tampil. 
    - Memastikan TextView untuk score sudah tampil. 
    - Memastikan ImageView untuk image sudah tampil. 

7. FavoriteMovieTest :
  - Memuat halaman FavoriteMovieTest.
  - Memastikan RecylerView dalam keadaan tampil.

8. FavoriteTvTest :
  - Memuat halaman FavoriteTvTest.
  - Memastikan RecylerView dalam keadaan tampil.