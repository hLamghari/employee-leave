import { Component, Input } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Leave } from "@models/leave.model";
import { EmployeService } from "@services/employe.service";

@Component({
    selector: "conge-list",
    templateUrl: './conge-list.component.html',
    styleUrls: ['./conge-list.component.css']
})
export class CongeListComponent {

    @Input()
    emplyeeId!: number;

    leaves: Leave[] = [];
    constructor(private employeeService: EmployeService, private route: ActivatedRoute) {}

    ngOnInit() {
        this.loadConges(Number(this.route.snapshot.paramMap.get('id')));
    }

    loadConges(employeeId: number): void {
        this.employeeService.getEmployeeConges(employeeId).subscribe(
            response => {
                this.leaves = response.leaves;
            }
        );
    }
}