import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserInterface} from "./Interfaces/UserInterface";
import {UsersDashboardService} from "./Services/users-dashboard.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {debounceTime, distinctUntilChanged, filter, fromEvent, tap} from "rxjs";

@Component({
  selector: 'app-users-dashboard',
  templateUrl: './users-dashboard.component.html'
})
export class UsersDashboardComponent implements OnInit, OnDestroy {


  // PROPERTIES
  users: UserInterface[] = [];
  viewTarget: UserInterface | null = null;
  editTarget: UserInterface | null = null;
  @ViewChild('searchInput') searchInputValue: ElementRef | undefined;
  editForm : FormGroup = this.formBuilder.group({
    nom: [this.editTarget?.nom],
    prenom: [this.editTarget?.prenom],
    email: [this.editTarget?.email],
    role: [this.editTarget?.role],
  });

  addForm: FormGroup = this.formBuilder.group({
    nom: [''],
    prenom: [''],
    email: [''],
    password: [''],
  }
  );

  // CONSTRUCTOR
  constructor(
    private httpClient: HttpClient,
    private usersDashboardService: UsersDashboardService,
    private formBuilder: FormBuilder,
  ) {
  }


  // LIFECYCLE HOOKS
  ngOnInit(): void {
    this.usersDashboardService.getAllUsers().subscribe(
      (data) => {
        this.users = data.users;
      }
    )
  }
  ngAfterViewInit() {
    fromEvent(this.searchInputValue?.nativeElement,'keyup')
      .pipe(
        filter(Boolean),
        debounceTime(150),
        distinctUntilChanged(),
        tap(() => {
          console.log(this.searchInputValue?.nativeElement.value)
        })
      )
      .subscribe(
        () => {
          this.usersDashboardService.searchUser(this.searchInputValue?.nativeElement.value).subscribe(
            (data) => {
              this.users = data.users;
            }
          )
        }
      );
  }
  ngOnDestroy(): void {
    this.viewTarget = null;
    this.editTarget = null;
    this.users = [];
  }

  // METHODS
  toggleBackgroundBlur(blur: boolean): void {
    if (blur) {
      const bg = document.getElementById('bg');
      bg?.classList.add('bg-black', 'opacity-50', 'top-0', 'left-0', 'w-full', 'h-full', 'fixed', 'z-40');
    } else {
      const bg = document.getElementById('bg');
      bg?.classList.remove('bg-black', 'opacity-50', 'top-0', 'left-0', 'w-full', 'h-full', 'fixed', 'z-40');
    }
  }

  openViewModal(id: number): void {
    const modal = document.getElementById('viewUserModal');
    modal?.classList.remove('hidden');
    this.toggleBackgroundBlur(true);
    this.viewTarget = this.users.find(user => user.id == id) as UserInterface;
    console.log(this.viewTarget);

  }

  closeViewModal() {
    const modal = document.getElementById('viewUserModal');
    modal?.classList.add('hidden');
    this.toggleBackgroundBlur(false);
    this.viewTarget = null;
  }

  openEditModal(user: UserInterface): void {
    if (user.role == "ROLE_ADMIN") {
      alert("Vous ne pouvez pas modifier un admin");
      return;
    }
    this.editTarget = this.users.find(u => u.id == user.id) as UserInterface;
    const modal = document.getElementById('editUserModal');
    modal?.classList.remove('hidden');
    this.toggleBackgroundBlur(true);
    console.log(this.editTarget);
  }

  closeEditModal() {
    const modal = document.getElementById('editUserModal');
    modal?.classList.add('hidden');
    this.toggleBackgroundBlur(false);
    this.editTarget = null;
  }

  deleteUser(id: number) {
    let user = this.users.find(user => user.id == id) as UserInterface;
    if (user.role == "ROLE_ADMIN") {
      alert("You cannot delete an admin");
      return;
    }

    if (user.role == "ROLE_UTILISATEUR") {
      this.usersDashboardService.deleteUserById(id).subscribe(
        (data) => {
          console.log(data);
          this.usersDashboardService.getAllUsers().subscribe(
            (data) => {
              this.users = data.users;
            }
          );
        }
      );
    }
  }

  onEditFormSubmit(userId: number | undefined) {
    let userData = {
      nom : this.editForm.value.nom,
      prenom: this.editForm.value.prenom,
      email: this.editForm.value.email,
      role: this.editForm.value.role,
    }
    console.log(userData);
    this.usersDashboardService.updateUser(userId, userData).subscribe(
      (data) => {
        console.log(data);
        this.usersDashboardService.getAllUsers().subscribe(
          (data) => {
            this.users = data.users;
          }
        )
      }
    );
    this.closeEditModal();
  }

  onAddFormSubmit() {
    let userData = {
      nom : this.addForm.value.nom,
      prenom: this.addForm.value.prenom,
      email: this.addForm.value.email,
      password: this.addForm.value.password,
    }
    this.usersDashboardService.addUser(userData).subscribe(
      (data) => {
        console.log(data);
        this.usersDashboardService.getAllUsers().subscribe(
          (data) => {
            this.users = data.users;
          }
        )
      }
    );
    this.closeAddModal();
  }

  closeAddModal() {
    const modal = document.getElementById('addUserModal');
    modal?.classList.add('hidden');
    this.toggleBackgroundBlur(false);
  }

  openAddModal() {
    const modal = document.getElementById('addUserModal');
    modal?.classList.remove('hidden');
    this.toggleBackgroundBlur(true);
  }
}
