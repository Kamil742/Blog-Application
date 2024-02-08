"use client";
import { usePathname } from "next/navigation";
import React from "react";
import styles from "./navbar.module.css";
import { MdOutlineChat, MdPublic, MdSearch } from "react-icons/md";
import { MdNotification } from "react-icons/md";

const Navbar = () => {
  const pathname = usePathname();
  return (
    <div className={styles.container}>
      <div className={styles.title}>{pathname.split("/").pop()}</div>
      <div className={styles.menu}>
        <div className={styles.search}>
          <MdSearch />
          <input
            type="text"
            placeholder="search.."
            className={styles.input}
          ></input>
        </div>
        <div className={styles.icons}>
          <MdOutlineChat size={20}></MdOutlineChat>
          <MdPublic size={20}></MdPublic>
        </div>
      </div>
    </div>
  );
};

export default Navbar;
