"use client";
import React from "react";
import Link from "next/link";
import styles from "./menuLink.module.css";
import { usePathname } from "next/navigation";

const MenuLink = ({ items }) => {
  const pathname = usePathname();
  console.log(pathname);
  return (
    <Link
      href={items.path}
      className={`${styles.container} ${
        pathname === items.path && styles.active
      }`}
    >
      {items.icon}
      {items.title}
    </Link>
  );
};

export default MenuLink;
