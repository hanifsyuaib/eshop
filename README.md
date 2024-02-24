# Tutorial-3

## Reflection
1. Pada project eshop, saya telah menerapkan Single responsibility principle (SRP), Open-closed principle (OCP), 
Liskov-substitution principle (LSP), Interface segregation principle(ISP), dan Dependency iversion principle(DIP). 
SRP telah diterapkan pada setiap class termasuk pada testing dan CarController telah dipindahkan menjadi file tersendiri.
OCP telah diterapkan pada controller, service, repository, dan model. LSP telah diterapkan pada service. ISP telah 
diterapkan pada Controller dan Service. DIP telah diterapkan pada controller dan service.

2. Penerapan SRP dapat memberi keuntungan untuk mempermudah membaca kode dan maintain kode, contohnya pada productService
dan carService dimana create,edit,dan delete terpisah sehingga mudah dipahami. Penerapan OCP dapat memberi keuntungan
untuk menambah kemampuan kode sekaligus mengurangi resiko bugs, contohnya jika ada yang ingin ditambahkan selain yang udah
ada maka bisa langsung saja ditambahkan ke controller, service, repository, dan model. Penerapan LSP dapat memberi 
keuntungan untuk penukaran antara child atau parent class tidak akan membuat program error, contohnya pada 
car controller yang menginherit product controller. Penerapan ISP dapat memberi keuntungan untuk cukup membuat interface
yang diperlukan dimana hal ini mirip seperti SRP, contohnya pada product service dan car service memuat interface yang
bekerja hanya sesuai dengan fungsionalitasnya saja. Penerapan DIP dapat memberi keuntungan untuk bergantung pada 
abstraction daripada implementasi seperti objek, contohnya controller dan service bergantung pada interface product service
dan car service.

3. Tidak menerapkan SRP dapat membuat class dengan banyak pekerjaan untuk sulit dipahami dan mantain, contohnya
controller bisa saja mengurus bagian business logic juga yang tentunya akan lebih sulit di mantain dan test.
Tidak menerapkan OCP dapat berakibat memunculkan bugs yang semulanya tidak ada, contohnya penambahan fitur baru pada
product dan car dengan mengubah seluruh kode termasuk outputnya dapat memunculkan bugs yang seharusnya tidak muncul.
Tidak menerapkan LSP dapat memunculkan error, contohnya menukar objek class car dengan product dapat memunculkan error.
Tidak menerapkan ISP dapat memunculkan code bloat dengan adanya method-method yang tidak dipakai, contohnya terdapat
banyak interface yang tidak terpakai pada car service dan product service. Tidak menerapkan DIP dapat memunculkan
coupling menjadi tinggi dan fleksibilitas kode menurun, contohnya controller langsung membuat objek repository dimana
hal ini dapat membuat sulit untuk disubsitusi dengan repository lainnya dan sulit dalam melakukan testing.


# Tutorial-2

## Reflection

1. Pertama, duplikasi "redirect:list" sebanyak 3 kali. Hal ini membuat setiap perubahan terjadi saya harus mengganti 
sebanyak tiga kali. Code smell ini memiliki severity berupa critical. Dengan begitu, saya memutuskan untuk memakai 
constant bernama REDIRECT_TO_LIST sehingga setiap perubahan yang diinginkan cukup dilakukan sekali saja pada 
constant REDIRECT_TO_LIST.
    
    Kedua dan ketiga saya memperbaiki permasalahan yang sama, yaitu pemakaian field injection (@Autowired).
Field injection bisa bikin pembuatan object menjadi invalid state dan membuat testing lebih susah. Selain itu,
tidak compatible dengan field final dan dependencies tidak bisa explicit ketika instantiating sebuah class yang
memakai field injection. Code smell ini memiliki severity berupa major. Oleh karena itu, saya menyelasaikan masalah
ini dengan menggantikan field injection dengan constructor injection. Karena dependencies nya explicit dan dipakai
ketika object construction, sehingga menghindari instantiating object yang invalid state dan testing menjadi lebih mudah.


2. Iya, untuk continuous integration telah menjalankan build dan testing dengan berhasil secara otomatis setiap commit.
Selain itu, integrasi dengan version control berhasil juga ditandai dengan build dan testing bekerja setiap commit.
Untuk continuous deployment telah berhasil menjalankan deployment secara otomatis setiap commit setelah 
menjalankan build dan test terlebih dahulu. Proses integration dilakukan di branch bukan main, yaitu ci-cd dan 
module-2-exercise. Proses Depolyment dilakukan di satu branch utama, yaitu main. Pada branch main, semua unit test
berhasil berjalan dan code bisa dianalisis oleh sonarcloud serta deployment ke koyeb berhasil bisa diakses secara publik.

Note: Pada code coverage, dimulai dari 50% (Tutorial 1). Setelah penambahan unit test menjadi 99% (jacoco report) atau
97.2% (sonarcloud). Kini menjadi 97.3% (sonarcloud) setelah fix code quality issue. 
Untuk URL app adalah https://eshop-hanifsyuaib.koyeb.app/


# Tutorial-1

## Reflection 1

Berdasarkan clean code principles, saya telah menerapkan Meaningful Names dimana memastikan pemberian nama variabel yang
informatif sehingga tidak membingungkan pembaca. Selain itu, membuat function yang bernama jelas, ukuran kecil, dan 
bekerja dengan hanya 1 tujuan. Lalu, tidak memberikan comment karena sudah cukup jelas dengan kode yang tertera. Kemudian,
Memakai blank lines sehingga mempermudah dalam membaca kode. Setelah itu, tidak memanggil setter or getter terlalu sering
sehingga nilai private variable tetap terjaga dari public. Berdasarkan secure coding practices, saya telah menerapkan
input data validation sehingga harus memberikan input sesuai jenis field-nya. Untuk Authorization, semua orang bisa
menggunakan karena tidak ada roles khusus.

## Reflection 2

Menurut saya hal tersebut tidak bagus untuk dilakukan pada functional test yang baru dibuat. Kode yang baru dibuat
dapat menurunkan kualitas keseluruhun kode. Dengan setup procedures dan instance variables yang sama juga dapat
terjadinya duplikasi pada kode. Dampak dari duplikasi kode akan menyulitkan dalam melakukan mantain kode dan meningkatkan
kemungkinan terjadi error saat perubahan pada salah satu functional test terutama bagian setup procedure. Improvement
yang terpikirkan adalah dengan membuat reusable method untuk setup procedures dan instance variables sehingga
tidak perlu melakukan duplikasi kode. Selain itu, dengan menggunakan setup procedures dan instance variables yang sama
bisa menghadirkan test yang tidak ada hubungannya sama sekali. Hal ini dapat mengalihkan fokus dari pekerjaan test 
semestinya. Improvement yang dapat dilakukan adalah dengan menghapus atau memindahkan ke test tersendiri sehingga 
test bisa fokus dalam verifikasi jumlah item didalam list product.


## Getting started

To make it easy for you to get started with GitLab, here's a list of recommended next steps.

Already a pro? Just edit this README.md and make it your own. Want to make it easy? [Use the template at the bottom](#editing-this-readme)!

## Add your files

- [ ] [Create](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#create-a-file) or [upload](https://docs.gitlab.com/ee/user/project/repository/web_editor.html#upload-a-file) files
- [ ] [Add files using the command line](https://docs.gitlab.com/ee/gitlab-basics/add-file.html#add-a-file-using-the-command-line) or push an existing Git repository with the following command:

```
cd existing_repo
git remote add origin https://gitlab.cs.ui.ac.id/hanif.ibrahim01/tutorial-1.git
git branch -M main
git push -uf origin main
```

## Integrate with your tools

- [ ] [Set up project integrations](https://gitlab.cs.ui.ac.id/hanif.ibrahim01/tutorial-1/-/settings/integrations)

## Collaborate with your team

- [ ] [Invite team members and collaborators](https://docs.gitlab.com/ee/user/project/members/)
- [ ] [Create a new merge request](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html)
- [ ] [Automatically close issues from merge requests](https://docs.gitlab.com/ee/user/project/issues/managing_issues.html#closing-issues-automatically)
- [ ] [Enable merge request approvals](https://docs.gitlab.com/ee/user/project/merge_requests/approvals/)
- [ ] [Set auto-merge](https://docs.gitlab.com/ee/user/project/merge_requests/merge_when_pipeline_succeeds.html)

## Test and Deploy

Use the built-in continuous integration in GitLab.

- [ ] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/index.html)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing (SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)
- [ ] [Deploy to Kubernetes, Amazon EC2, or Amazon ECS using Auto Deploy](https://docs.gitlab.com/ee/topics/autodevops/requirements.html)
- [ ] [Use pull-based deployments for improved Kubernetes management](https://docs.gitlab.com/ee/user/clusters/agent/)
- [ ] [Set up protected environments](https://docs.gitlab.com/ee/ci/environments/protected_environments.html)

***

# Editing this README

When you're ready to make this README your own, just edit this file and use the handy template below (or feel free to structure it however you want - this is just a starting point!). Thanks to [makeareadme.com](https://www.makeareadme.com/) for this template.

## Suggestions for a good README

Every project is different, so consider which of these sections apply to yours. The sections used in the template are suggestions for most open source projects. Also keep in mind that while a README can be too long and detailed, too long is better than too short. If you think your README is too long, consider utilizing another form of documentation rather than cutting out information.

## Name
Choose a self-explaining name for your project.

## Description
Let people know what your project can do specifically. Provide context and add a link to any reference visitors might be unfamiliar with. A list of Features or a Background subsection can also be added here. If there are alternatives to your project, this is a good place to list differentiating factors.

## Badges
On some READMEs, you may see small images that convey metadata, such as whether or not all the tests are passing for the project. You can use Shields to add some to your README. Many services also have instructions for adding a badge.

## Visuals
Depending on what you are making, it can be a good idea to include screenshots or even a video (you'll frequently see GIFs rather than actual videos). Tools like ttygif can help, but check out Asciinema for a more sophisticated method.

## Installation
Within a particular ecosystem, there may be a common way of installing things, such as using Yarn, NuGet, or Homebrew. However, consider the possibility that whoever is reading your README is a novice and would like more guidance. Listing specific steps helps remove ambiguity and gets people to using your project as quickly as possible. If it only runs in a specific context like a particular programming language version or operating system or has dependencies that have to be installed manually, also add a Requirements subsection.

## Usage
Use examples liberally, and show the expected output if you can. It's helpful to have inline the smallest example of usage that you can demonstrate, while providing links to more sophisticated examples if they are too long to reasonably include in the README.

## Support
Tell people where they can go to for help. It can be any combination of an issue tracker, a chat room, an email address, etc.

## Roadmap
If you have ideas for releases in the future, it is a good idea to list them in the README.

## Contributing
State if you are open to contributions and what your requirements are for accepting them.

For people who want to make changes to your project, it's helpful to have some documentation on how to get started. Perhaps there is a script that they should run or some environment variables that they need to set. Make these steps explicit. These instructions could also be useful to your future self.

You can also document commands to lint the code or run tests. These steps help to ensure high code quality and reduce the likelihood that the changes inadvertently break something. Having instructions for running tests is especially helpful if it requires external setup, such as starting a Selenium server for testing in a browser.

## Authors and acknowledgment
Show your appreciation to those who have contributed to the project.

## License
For open source projects, say how it is licensed.

## Project status
If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.
