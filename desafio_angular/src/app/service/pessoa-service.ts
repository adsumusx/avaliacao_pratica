import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { config } from '../../config';
import { Page } from '../model/page';
import { PagedData } from '../model/paged-data';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { Pessoa } from '../model/pessoa';

const endpoint = config.apiUrlBase + '/pessoa/';
const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class PessoaService {
    pessoa: any;
    page: Page = new Page;
    constructor(private http: HttpClient) { }

    listarPessoas(page: Page): Observable<PagedData<Pessoa>> {
        this.page = page;
        return this.http.get(endpoint + "listaPessoa").pipe(map((res: Response) => this.extractData(res)));
    }

    deletePessoa(id): Observable<any> {
        return this.http.delete<any>(endpoint + 'deletePessoa/' + id, httpOptions).pipe(

            tap(_ => `delete id=${id}`),
            catchError(this.handleError<any>('delete', null))
        );
    }

    getPessoa(id): Observable<any> {
        return this.http.get(endpoint + 'getPessoa/' + id).pipe(
            map((res: Response) => res));
    }

    updatePessoa(id, pessoa): Observable<any> {
        return this.http.put(endpoint + 'putPessoa/' + id, JSON.stringify(pessoa), httpOptions).pipe(
            tap(_ => console.log(`Pessoa Editado`)),
            catchError(this.handleError<any>('updatePessoa'))
        );
    }

    addPessoa(Pessoa): Observable<any> {
        return this.http.post<any>(endpoint + 'setPessoa', JSON.stringify(Pessoa), httpOptions)
            .pipe(
                tap((Pessoa) => console.log(Pessoa)),
                catchError(this.handleError<any>('setPessoa', Pessoa))
            );
    }



    extractData(res: Response): PagedData<Pessoa> {

        let pagedData = new PagedData<Pessoa>();
        if (res != null) {
            let body = res;
            this.pessoa = body;
            this.page.totalElements = (this.pessoa != null ? this.pessoa.result_count : 0);

            if (this.pessoa.result_count != null) {
                this.page.totalPages = this.page.totalElements / this.page.size;
                pagedData.result = res;
            }
            if (this.page.size != 0) {
                this.page.offset = this.page.pageNumber * this.page.size;
                this.page.limit = Math.min((this.page.offset + this.page.size), this.page.totalElements); //end
            }
            else {
                this.page.limit = this.pessoa.result_count;
                this.page.offset = 0;

            }
            pagedData.data = this.pessoa.results;

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
