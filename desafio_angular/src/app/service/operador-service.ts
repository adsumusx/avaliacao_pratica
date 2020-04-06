import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { config } from '../../config';
import { Page } from '../model/page';
import { PagedData } from '../model/paged-data';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { Operador } from '../model/operador';

const endpoint = config.apiUrlBase + '/operador/';
const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class OperadorService {
    operador: any;
    page: Page = new Page;
    constructor(private http: HttpClient) { }

    listarOperadores(page: Page): Observable<PagedData<Operador>> {
        this.page = page;
        return this.http.get(endpoint + "listaOperador").pipe(map((res: Response) => this.extractData(res)));
    }

    deleteOperador(id): Observable<any> {
        return this.http.delete<any>(endpoint + 'deleteOperador/' + id, httpOptions).pipe(

            tap(_ => `delete id=${id}`),
            catchError(this.handleError<any>('delete', null))
        );
    }

    getOperador(id): Observable<any> {
        return this.http.get(endpoint + 'getOperador/' + id).pipe(
            map((res: Response) => res));
    }

    updateOperador(id, operador): Observable<any> {
        return this.http.put(endpoint + 'putOperador/' + id, JSON.stringify(operador), httpOptions).pipe(
            tap(_ => console.log(`Operador Editado`)),
            catchError(this.handleError<any>('updateOperador'))
        );
    }

    addOperador(Operador): Observable<any> {
        return this.http.post<any>(endpoint + 'setOperador', JSON.stringify(Operador), httpOptions)
            .pipe(
                tap((Operador) => console.log(Operador)),
                catchError(this.handleError<any>('setOperador', Operador))
            );
    }



    extractData(res: Response): PagedData<Operador> {

        let pagedData = new PagedData<Operador>();
        if (res != null) {
            let body = res;
            this.operador = body;
            this.page.totalElements = (this.operador != null ? this.operador.result_count : 0);

            if (this.operador.result_count != null) {
                this.page.totalPages = this.page.totalElements / this.page.size;
                pagedData.result = res;
            }
            if (this.page.size != 0) {
                this.page.offset = this.page.pageNumber * this.page.size;
                this.page.limit = Math.min((this.page.offset + this.page.size), this.page.totalElements); //end
            }
            else {
                this.page.limit = this.operador.result_count;
                this.page.offset = 0;

            }
            pagedData.data = this.operador.results;

            pagedData.page = this.page;
        }
        return pagedData;

    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead
            // TODO: better job of transforming error for user consumption
            console.log(`${operation} failed: ${error.message}`);

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }
}
